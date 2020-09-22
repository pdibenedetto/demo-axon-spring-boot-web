package com.github.ronlievens.demo.axon.web.axon.events;

import com.github.ronlievens.demo.axon.web.model.Currency;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class MoneyCreditedEvent {
    public final UUID id;
    public final double creditAmount;
    public final Currency currency;
}
