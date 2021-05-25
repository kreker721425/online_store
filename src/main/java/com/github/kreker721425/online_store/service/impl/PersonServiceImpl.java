package com.github.kreker721425.online_store.service.impl;

import com.github.kreker721425.online_store.dto.OrderDto;
import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.exception.NoFoundObjectException;
import com.github.kreker721425.online_store.mapper.OrderMapper;
import com.github.kreker721425.online_store.mapper.PersonMapper;
import com.github.kreker721425.online_store.mapper.ProductMapper;
import com.github.kreker721425.online_store.mapper.ReviewMapper;
import com.github.kreker721425.online_store.model.Person;
import com.github.kreker721425.online_store.model.Product;
import com.github.kreker721425.online_store.model.Review;
import com.github.kreker721425.online_store.repository.OrderRepository;
import com.github.kreker721425.online_store.repository.PersonRepository;
import com.github.kreker721425.online_store.repository.ProductRepository;
import com.github.kreker721425.online_store.repository.ReviewRepository;
import com.github.kreker721425.online_store.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;


    public PersonDto add(PersonDto personDto) {
        return personMapper.toPersonDto(personRepository.save(personMapper.toPerson(personDto)));
    }


    public void delete(PersonDto personDto) {
        List<ProductDto> cart = productRepository.findByPersons(personMapper.toPerson(personDto)).stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
        cart.forEach(product -> {
            product.getPersons().remove(personDto);
            productRepository.save(productMapper.toProduct(product));
        });

        List<OrderDto> ordersDto = orderRepository.findByPerson(personMapper.toPerson(personDto)).stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
        ordersDto.forEach(orderDto -> orderRepository.delete(orderMapper.toOrder(orderDto)));

        List<ReviewDto> reviewsDto = reviewRepository.findByPerson(personMapper.toPerson(personDto)).stream()
                .map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
        reviewsDto.forEach(reviewDto -> reviewRepository.delete(reviewMapper.toReview(reviewDto)));

        personDto.setCart(new ArrayList<>());
        personDto.setOrders(new ArrayList<>());
        personDto.setReviews(new ArrayList<>());
        personRepository.delete(personMapper.toPerson(personDto));
    }


    public PersonDto findById(Long id) {
        try {
            return personMapper.toPersonDto(personRepository.findById(id).orElseThrow(NoFoundObjectException::new));
        } catch (NoFoundObjectException e) {
            log.error("No found Person by id={} {}", id, e);
            return null;
        }
    }


    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toPersonDto)
                .collect(Collectors.toList());
    }

    public List<PersonDto> findAny(Long begin, Long limit) {
        return personRepository.findAny(begin, limit).stream()
                .map(personMapper::toPersonDto)
                .collect(Collectors.toList());
    }

    public PersonDto update(Long id, PersonDto personDto) {
        personDto.setId(id);
        return personMapper.toPersonDto(personRepository.save(personMapper.toPerson(personDto)));
    }

    public List<ReviewDto> findReviews(PersonDto personDto) {
        return reviewRepository.findByPerson(personMapper.toPerson(personDto)).stream()
                .map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findProducts(PersonDto personDto) {
        return productRepository.findByPersons(personMapper.toPerson(personDto)).stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public List<OrderDto> findOrders(PersonDto personDto) {
        return orderRepository.findByPerson(personMapper.toPerson(personDto)).stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
    }
}
