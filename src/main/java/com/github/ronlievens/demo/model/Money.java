package com.github.ronlievens.demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Money {
    private double amount;
    private Currency currency;
}
