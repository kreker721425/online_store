package com.github.kreker721425.online_store.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Category {

    CATEGORY1("category1"),
    CATEGORY2("category2"),
    CATEGORY3("category3");

    private String value;
}
