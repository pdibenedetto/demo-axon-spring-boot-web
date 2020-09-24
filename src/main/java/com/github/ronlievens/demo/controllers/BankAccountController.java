package com.github.ronlievens.demo.controllers;

import com.github.ronlievens.demo.model.Money;
import com.github.ronlievens.demo.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping
    public CompletableFuture<UUID> createAccount(@RequestBody NewBackAccountModel model) {
        return bankAccountService.createAccount(model.getName(), model.getCurrency(), model.getAmount());
    }

    @PutMapping(value = "/credits/{accountNumber}")
    public CompletableFuture<UUID> creditMoneyToAccount(@PathVariable(value = "accountNumber") UUID accountNumber,
                                                        @RequestBody Money moneyCreditDTO) {
        return bankAccountService.creditMoneyToAccount(accountNumber, moneyCreditDTO);
    }

    @PutMapping(value = "/debits/{accountNumber}")
    public CompletableFuture<UUID> debitMoneyFromAccount(@PathVariable(value = "accountNumber") UUID accountNumber,
                                                         @RequestBody Money moneyDebitDTO) {
        return bankAccountService.debitMoneyFromAccount(accountNumber, moneyDebitDTO);
    }

    @GetMapping("/{accountNumber}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountNumber") UUID accountNumber) {
        return bankAccountService.listEventsForAccount(accountNumber);
    }
}
