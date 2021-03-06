package com.github.ronlievens.demo.services;

import com.github.ronlievens.demo.model.Currency;
import com.github.ronlievens.demo.model.Money;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface BankAccountService {

    CompletableFuture<UUID> createAccount(String name, Currency currency, double amount);

    CompletableFuture<UUID> creditMoneyToAccount(UUID accountNumber, Money moneyCreditDTO);

    CompletableFuture<UUID> debitMoneyFromAccount(UUID accountNumber, Money moneyDebitDTO);

    List<Object> listEventsForAccount(UUID accountNumber);
}
