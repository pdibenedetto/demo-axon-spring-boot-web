package com.github.ronlievens.demo.axon.web.axon.events;

import com.github.ronlievens.demo.axon.web.model.Currency;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MoneyCreditedEvent {
    public final String id;
    public final double creditAmount;
    public final Currency currency;
}
