package com.github.ronlievens.demo.axon.web.axon.commands;

import com.github.ronlievens.demo.axon.web.model.Currency;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
public class CreateAccountCommand {
    @TargetAggregateIdentifier
    public final String id;
    public final double accountBalance;
    public final Currency currency;
}
