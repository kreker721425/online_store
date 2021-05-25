package com.github.kreker721425.online_store.service;

import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto add(ReviewDto reviewDto);

    void delete(ReviewDto reviewDto);

    ReviewDto findById(Long id);

    List<ReviewDto> findAll();

    ReviewDto update(Long id, ReviewDto reviewDto);

    ProductDto findProduct(ReviewDto reviewDto);

    PersonDto findPerson(ReviewDto reviewDto);
}
