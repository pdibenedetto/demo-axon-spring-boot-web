package com.github.ronlievens.demo.controllers;

import com.github.ronlievens.demo.model.Money;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class NewBackAccountModel extends Money {
    private String name;
}
