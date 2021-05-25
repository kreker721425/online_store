package com.github.kreker721425.online_store.controller;

import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewRestController {

    @Autowired
    ReviewService reviewService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDto>> getAll() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addReview(@RequestBody ReviewDto reviewDto) {
        reviewService.add(reviewDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReviewDto> getReview(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.update(id, reviewDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteReview(@PathVariable Long id) {
        reviewService.delete(reviewService.findById(id));
    }

    @RequestMapping(path = "/{id}/product", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findProduct(reviewService.findById(id)));
    }

    @RequestMapping(path = "/{id}/person", method = RequestMethod.GET)
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.findPerson(reviewService.findById(id)));
    }
}
