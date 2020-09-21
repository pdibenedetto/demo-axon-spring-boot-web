package com.github.ronlievens.demo.axon.web.axon.events;

import com.github.ronlievens.demo.axon.web.model.Status;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountHeldEvent {
    public final String id;
    public final Status status;
}
