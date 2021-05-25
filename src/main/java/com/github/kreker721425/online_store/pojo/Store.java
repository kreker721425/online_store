package com.github.kreker721425.online_store.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Store {

    STORE1("store1"),
    STORE2("store2"),
    STORE3("store3");

    private String value;
}
