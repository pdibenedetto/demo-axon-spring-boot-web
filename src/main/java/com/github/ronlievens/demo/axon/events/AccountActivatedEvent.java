package com.github.ronlievens.demo.axon.events;

import com.github.ronlievens.demo.model.Status;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AccountActivatedEvent {
    public final UUID id;
    public final Status status;
}
