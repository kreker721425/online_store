package com.github.kreker721425.online_store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Long price;
    private String img;
    private String description;
    private Long count;
    private String manufacturer;

    private String category;
    private List<ReviewDto> reviews = new ArrayList<>();
    private List<PersonDto> persons = new ArrayList<>();
    private List<OrderDto> orders = new ArrayList<>();
}
