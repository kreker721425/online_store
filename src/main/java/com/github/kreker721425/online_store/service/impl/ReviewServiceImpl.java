package com.github.kreker721425.online_store.service.impl;

import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.exception.NoFoundObjectException;
import com.github.kreker721425.online_store.mapper.PersonMapper;
import com.github.kreker721425.online_store.mapper.ProductMapper;
import com.github.kreker721425.online_store.mapper.ReviewMapper;
import com.github.kreker721425.online_store.model.Review;
import com.github.kreker721425.online_store.repository.PersonRepository;
import com.github.kreker721425.online_store.repository.ProductRepository;
import com.github.kreker721425.online_store.repository.ReviewRepository;
import com.github.kreker721425.online_store.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;


    public ReviewDto add(ReviewDto reviewDto) {
        reviewDto.setCreateDate(LocalDate.now());
        return reviewMapper.toReviewDto(reviewRepository.save(reviewMapper.toReview(reviewDto)));
    }

    public void delete(ReviewDto reviewDto) {
        PersonDto personDto = personMapper.toPersonDto(personRepository.findByReviews(reviewMapper.toReview(reviewDto)));
        personDto.getReviews().remove(reviewDto);
        personRepository.save(personMapper.toPerson(personDto));

        ProductDto productDto = productMapper.toProductDto(productRepository.findByReviews(reviewMapper.toReview(reviewDto)));
        productDto.getReviews().remove(reviewDto);
        productRepository.save(productMapper.toProduct(productDto));

        reviewDto.setPerson(null);
        reviewDto.setProduct(null);
        reviewRepository.delete(reviewMapper.toReview(reviewDto));
    }

    public ReviewDto findById(Long id) {
        try {
            return reviewMapper.toReviewDto(reviewRepository.findById(id).orElseThrow(NoFoundObjectException::new));
        } catch (NoFoundObjectException e) {
            log.error("No found Product by id={} {}", id, e);
            return null;
        }
    }

    public List<ReviewDto> findAll() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toReviewDto)
                .collect(Collectors.toList());
    }

    public ReviewDto update(Long id, ReviewDto reviewDto) {
        reviewDto.setId(id);
        return reviewMapper.toReviewDto(reviewRepository.save(reviewMapper.toReview(reviewDto)));
    }

    @Override
    public ProductDto findProduct(ReviewDto reviewDto) {
        return productMapper.toProductDto(productRepository.findByReviews(reviewMapper.toReview(reviewDto)));
    }

    @Override
    public PersonDto findPerson(ReviewDto reviewDto) {
        return personMapper.toPersonDto(personRepository.findByReviews(reviewMapper.toReview(reviewDto)));
    }
}
