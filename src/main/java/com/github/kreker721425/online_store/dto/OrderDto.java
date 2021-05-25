package com.github.kreker721425.online_store.dto;

import com.github.kreker721425.online_store.pojo.StatusOrder;
import com.github.kreker721425.online_store.pojo.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private String text;
    private Store store;
    private StatusOrder status;
    private LocalDateTime date;

    private PersonDto person;
    private List<ProductDto> cart = new ArrayList<>();
}
