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
public class PersonDto {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;

    private List<ReviewDto> reviews = new ArrayList<>();
    private List<ProductDto> cart = new ArrayList<>();
    private List<OrderDto> orders = new ArrayList<>();
}
