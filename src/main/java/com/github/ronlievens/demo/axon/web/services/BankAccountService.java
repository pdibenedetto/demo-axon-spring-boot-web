package com.github.ronlievens.demo.axon.web.services;

import com.github.ronlievens.demo.axon.web.model.Money;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface BankAccountService {

    CompletableFuture<UUID> createAccount(Money balance);

    CompletableFuture<UUID> creditMoneyToAccount(UUID accountNumber, Money moneyCreditDTO);

    CompletableFuture<UUID> debitMoneyFromAccount(UUID accountNumber, Money moneyDebitDTO);

    List<Object> listEventsForAccount(UUID accountNumber);
}
