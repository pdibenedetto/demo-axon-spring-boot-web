package com.github.ronlievens.demo.axon.events;

import com.github.ronlievens.demo.model.Currency;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AccountCreatedEvent {

    public final UUID id;
    public final double accountBalance;
    public final Currency currency;
}
