package com.github.ronlievens.demo.axon.web.axon.commands;

import com.github.ronlievens.demo.axon.web.model.Currency;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@RequiredArgsConstructor
public class CreditMoneyCommand {
    @TargetAggregateIdentifier
    public final UUID id;
    public final double creditAmount;
    public final Currency currency;
}
