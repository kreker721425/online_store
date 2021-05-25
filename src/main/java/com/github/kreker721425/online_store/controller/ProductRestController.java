package com.github.kreker721425.online_store.controller;

import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    /*@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getAny(@PathVariable Long begin) {
        return ResponseEntity.ok(productService.findAny(begin, limit));
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public void addProduct(@RequestBody ProductDto product) {
        productService.save(product);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(productService.findById(id));
    }

    @RequestMapping(path = "/{id}/reviews", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDto>> getReviewsByProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findReviews(productService.findById(id)));
    }
}
