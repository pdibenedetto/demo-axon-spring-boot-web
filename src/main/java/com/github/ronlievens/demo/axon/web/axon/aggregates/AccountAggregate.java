package com.github.ronlievens.demo.axon.web.axon.aggregates;

import com.github.ronlievens.demo.axon.web.axon.commands.CreateAccountCommand;
import com.github.ronlievens.demo.axon.web.axon.commands.CreditMoneyCommand;
import com.github.ronlievens.demo.axon.web.axon.commands.DebitMoneyCommand;
import com.github.ronlievens.demo.axon.web.axon.events.*;
import com.github.ronlievens.demo.axon.web.model.Currency;
import com.github.ronlievens.demo.axon.web.model.Status;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@NoArgsConstructor
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private UUID id;
    private double accountBalance;
    private Currency currency;
    private String status;

    // --[ Create Account ]---------------------------------------------------------------------------------------------
    @CommandHandler
    public AccountAggregate(final CreateAccountCommand createAccountCommand) {
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id,
                                                         createAccountCommand.accountBalance,
                                                         createAccountCommand.currency));
    }

    @EventSourcingHandler
    protected void on(final AccountCreatedEvent accountCreatedEvent) {
        this.id = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);

        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    }

    // --[ Account Activated ]------------------------------------------------------------------------------------------
    @EventSourcingHandler
    protected void on(final AccountActivatedEvent accountActivatedEvent) {
        this.status = String.valueOf(accountActivatedEvent.status);
    }

    @CommandHandler
    protected void on(final CreditMoneyCommand creditMoneyCommand) {
        AggregateLifecycle.apply(new MoneyCreditedEvent(creditMoneyCommand.id,
                                                        creditMoneyCommand.creditAmount,
                                                        creditMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(final MoneyCreditedEvent moneyCreditedEvent) {

        if (this.accountBalance < 0 & (this.accountBalance + moneyCreditedEvent.creditAmount) >= 0) {
            AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
        }

        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @CommandHandler
    protected void on(final DebitMoneyCommand debitMoneyCommand) {
        AggregateLifecycle.apply(new MoneyDebitedEvent(debitMoneyCommand.id,
                                                       debitMoneyCommand.debitAmount,
                                                       debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(final MoneyDebitedEvent moneyDebitedEvent) {

        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0) {
            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
        }

        this.accountBalance -= moneyDebitedEvent.debitAmount;
    }

    @EventSourcingHandler
    protected void on(final AccountHeldEvent accountHeldEvent) {
        this.status = String.valueOf(accountHeldEvent.status);
    }
}
