package com.github.kreker721425.online_store.service;

import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;

import java.util.List;

public interface PersonService {

    PersonDto add(PersonDto productDto);

    void delete(PersonDto productDto);

    PersonDto findById(Long id);

    List<PersonDto> findAll();

    List<PersonDto> findAny(Long begin, Long limit);

    PersonDto update(Long id, PersonDto personDto);

    List<ReviewDto> findReviews(PersonDto personDto);

    List<ProductDto> findProducts(PersonDto personDto);
}
