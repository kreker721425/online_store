package com.github.kreker721425.online_store.controller;

import com.github.kreker721425.online_store.dto.OrderDto;
import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.pojo.Store;
import com.github.kreker721425.online_store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    
    @Autowired
    OrderService orderService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    /*@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderDto>> getAny(@PathVariable Long begin) {
        return ResponseEntity.ok(orderService.findAny(begin, limit));
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestParam String store, @RequestBody OrderDto order) {
        orderService.save(order, store);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OrderDto> update(@PathVariable Long id,
                                           @RequestParam String store,
                                           @RequestBody OrderDto order) {
        return ResponseEntity.ok(orderService.update(id, order, store));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        orderService.delete(orderService.findById(id));
    }

    @RequestMapping(path = "/{id}/products", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findCart(orderService.findById(id)));
    }

    @RequestMapping(path = "/{id}/person", method = RequestMethod.GET)
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findPerson(orderService.findById(id)));
    }
}
