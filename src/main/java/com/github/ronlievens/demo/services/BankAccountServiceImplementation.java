package com.github.ronlievens.demo.services;


import com.github.ronlievens.demo.axon.commands.CreateAccountCommand;
import com.github.ronlievens.demo.axon.commands.CreditMoneyCommand;
import com.github.ronlievens.demo.axon.commands.DebitMoneyCommand;
import com.github.ronlievens.demo.model.Currency;
import com.github.ronlievens.demo.model.Money;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class BankAccountServiceImplementation implements BankAccountService {

    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @Override
    public CompletableFuture<UUID> createAccount(final String name, final Currency currency, final double amount) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID(),
                                                            name,
                                                            amount,
                                                            currency));
    }

    @Override
    public CompletableFuture<UUID> creditMoneyToAccount(final UUID accountNumber, final Money money) {
        return commandGateway.send(new CreditMoneyCommand(accountNumber,
                                                          money.getAmount(),
                                                          money.getCurrency()));
    }

    @Override
    public CompletableFuture<UUID> debitMoneyFromAccount(final UUID accountNumber, final Money money) {
        return commandGateway.send(new DebitMoneyCommand(accountNumber,
                                                         money.getAmount(),
                                                         money.getCurrency()));
    }


    @Override
    public List<Object> listEventsForAccount(final UUID accountNumber) {
        return eventStore.readEvents(accountNumber.toString())
                         .asStream()
                         .map(s -> s.getPayload())
                         .collect(Collectors.toList());
    }
}
