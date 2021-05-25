package com.github.kreker721425.online_store.service;

import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;

import java.util.List;

public interface ProductService {

    ProductDto save(ProductDto productDto);

    void delete(ProductDto productDto);

    ProductDto findById(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findAny(Long begin, Long limit);

    ProductDto update(Long id, ProductDto productDto);

    List<ReviewDto> findReviews(ProductDto productDto);
}
