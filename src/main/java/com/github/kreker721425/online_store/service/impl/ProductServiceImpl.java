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
import com.github.kreker721425.online_store.repository.OrderRepository;
import com.github.kreker721425.online_store.repository.PersonRepository;
import com.github.kreker721425.online_store.repository.ProductRepository;
import com.github.kreker721425.online_store.repository.ReviewRepository;
import com.github.kreker721425.online_store.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;


    public ProductDto save(ProductDto productDto) {
        productDto.setCategory("category1");
        return productMapper.toProductDto(productRepository.save(productMapper.toProduct(productDto)));
    }

    public void delete(ProductDto productDto) {
        List<ReviewDto> reviewsDto = reviewRepository.findByProduct(productMapper.toProduct(productDto)).stream()
                .map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
        reviewsDto.forEach(reviewDto -> reviewRepository.delete(reviewMapper.toReview(reviewDto)));

        List<OrderDto> ordersDto = orderRepository.findByCart(productMapper.toProduct(productDto)).stream()
                .map(orderMapper::toOrderDto)
                .collect(Collectors.toList());
        ordersDto.forEach(orderDto -> orderRepository.delete(orderMapper.toOrder(orderDto)));

        List<PersonDto> persons = personRepository.findByCart(productMapper.toProduct(productDto)).stream()
                .map(personMapper::toPersonDto)
                .collect(Collectors.toList());
        persons.forEach(person -> {
            person.getCart().remove(productDto);
            personRepository.save(personMapper.toPerson(person));
        });

        productDto.setReviews(new ArrayList<>());
        productDto.setOrders(new ArrayList<>());
        productDto.setPersons(new ArrayList<>());
        productRepository.delete(productMapper.toProduct(productDto));
    }

    public ProductDto findById(Long id) {
        try {
            return productMapper.toProductDto(productRepository.findById(id).orElseThrow(NoFoundObjectException::new));
        } catch (NoFoundObjectException e) {
            log.error("No found Product by id={} {}", id, e);
            return null;
        }
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public List<ProductDto> findAny(Long begin, Long limit) {
        return productRepository.findAny(begin, limit).stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    public ProductDto update(Long id, ProductDto productDto) {
        productDto.setId(id);
        return productMapper.toProductDto(productRepository.save(productMapper.toProduct(productDto)));
    }

    public List<ReviewDto> findReviews(ProductDto productDto) {
        return reviewRepository.findByProduct(productMapper.toProduct(productDto)).stream()
                .map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
    }
}
