package com.github.kreker721425.online_store.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  StatusOrder {

    ACTIVE("Ждет получения"),
    FAIL("Не получен"),
    COMPLETE("Получен");

    private String value;
}
