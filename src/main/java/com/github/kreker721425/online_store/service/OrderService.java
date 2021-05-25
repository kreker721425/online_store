package com.github.kreker721425.online_store.service;

import com.github.kreker721425.online_store.dto.OrderDto;
import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;

import java.util.List;

public interface OrderService {

    OrderDto save(OrderDto orderDto, String store);

    void delete(OrderDto orderDto);

    void complete(OrderDto orderDto);

    void fail(OrderDto orderDto);

    OrderDto findById(Long id);

    List<OrderDto> findAll();

    List<OrderDto> findAny(Long begin, Long limit);

    OrderDto update(Long id, OrderDto orderDto, String store);

    PersonDto findPerson(OrderDto orderDto);

    List<ProductDto> findCart(OrderDto orderDto);
}
