package com.github.kreker721425.online_store.service.impl;

import com.github.kreker721425.online_store.dto.OrderDto;
import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.exception.NoFoundObjectException;
import com.github.kreker721425.online_store.mapper.OrderMapper;
import com.github.kreker721425.online_store.mapper.PersonMapper;
import com.github.kreker721425.online_store.mapper.ProductMapper;
import com.github.kreker721425.online_store.pojo.StatusOrder;
import com.github.kreker721425.online_store.pojo.Store;
import com.github.kreker721425.online_store.repository.OrderRepository;
import com.github.kreker721425.online_store.repository.PersonRepository;
import com.github.kreker721425.online_store.repository.ProductRepository;
import com.github.kreker721425.online_store.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;


    @Override
    public OrderDto save(OrderDto orderDto, String store) {
        orderDto.setStore(getStore(store));
        try {
            orderDto.getCart().forEach(productDto -> productDto.setCount(productDto.getCount() - 1));
        } catch (NullPointerException e) {
            log.error("" + e);
            return null;
        }
        orderDto.setStatus(StatusOrder.ACTIVE);
        orderDto.setDate(LocalDateTime.now());
        return orderMapper.toOrderDto(orderRepository.save(orderMapper.toOrder(orderDto)));
    }

    @Override
    public void delete(OrderDto orderDto) {
        PersonDto personDto = personMapper.toPersonDto(personRepository.findByOrders(orderMapper.toOrder(orderDto)));
        personDto.getOrders().remove(orderDto);
        personRepository.save(personMapper.toPerson(personDto));

        List<ProductDto> productsDto = productRepository.findByOrders(orderMapper.toOrder(orderDto)).stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
        productsDto.forEach(productDto -> {
            productDto.getOrders().remove(orderDto);
            productRepository.save(productMapper.toProduct(productDto));
        });

        orderDto.setPerson(null);
        orderDto.setCart(new ArrayList<>());
        orderRepository.delete(orderMapper.toOrder(orderDto));
    }

    @Override
    public void complete(OrderDto orderDto) {
        orderDto.setStatus(StatusOrder.COMPLETE);
        orderRepository.save(orderMapper.toOrder(orderDto));
    }

    @Override
    public void fail(OrderDto orderDto) {
        orderDto.setStatus(StatusOrder.FAIL);
        orderRepository.save(orderMapper.toOrder(orderDto));
    }

    @Override
    public OrderDto findById(Long id) {
        try {
            return orderMapper.toOrderDto(orderRepository.findById(id).orElseThrow(NoFoundObjectException::new));
        } catch (NoFoundObjectException e) {
            log.error("No found Order by id={} {}", id, e);
            return null;
        }
    }

    @Override
    public List<OrderDto> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findAny(Long begin, Long limit) {
        return orderRepository.findAny(begin, limit).stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto update(Long id, OrderDto orderDto, String store) {
        orderDto.setId(id);
        orderDto.setStore(getStore(store));
        return orderMapper.toOrderDto(orderRepository.save(orderMapper.toOrder(orderDto)));
    }

    @Override
    public PersonDto findPerson(OrderDto orderDto) {
        return personMapper.toPersonDto(personRepository.findByOrders(orderMapper.toOrder(orderDto)));
    }

    @Override
    public List<ProductDto> findCart(OrderDto orderDto) {
        return productRepository.findByOrders(orderMapper.toOrder(orderDto)).stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    private Store getStore(String store) {
        if (Objects.isNull(store)) {
            return null;
        }
        return Arrays.stream(Store.values())
                .filter(value -> StringUtils.equals(value.getValue(), store))
                .findFirst()
                .orElse(null);
    }
}
