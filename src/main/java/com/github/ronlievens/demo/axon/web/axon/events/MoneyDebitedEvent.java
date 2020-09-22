package com.github.ronlievens.demo.axon.web.axon.events;

import com.github.ronlievens.demo.axon.web.model.Currency;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class MoneyDebitedEvent {
    public final UUID id;
    public final double debitAmount;
    public final Currency currency;
}
