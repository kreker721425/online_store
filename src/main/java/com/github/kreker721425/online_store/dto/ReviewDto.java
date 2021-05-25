package com.github.kreker721425.online_store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;
    private Double rating;
    private String text;
    private LocalDate createDate;

    ProductDto product;
    PersonDto person;
}
