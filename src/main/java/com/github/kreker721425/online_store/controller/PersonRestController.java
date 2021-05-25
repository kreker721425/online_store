package com.github.kreker721425.online_store.controller;

import com.github.kreker721425.online_store.dto.ProductDto;
import com.github.kreker721425.online_store.dto.ReviewDto;
import com.github.kreker721425.online_store.dto.PersonDto;
import com.github.kreker721425.online_store.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

    @Autowired
    PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PersonDto>> getAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addPerson(@RequestBody PersonDto personDto) {
        personService.add(personDto);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.update(id, personDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable Long id) {
        personService.delete(personService.findById(id));
    }

    @RequestMapping(path = "/{id}/reviews", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDto>> getReviewsByPerson(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findReviews(personService.findById(id)));
    }

    @RequestMapping(path = "/{id}/cart", method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findProducts(personService.findById(id)));
    }
}
