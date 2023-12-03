package com.vorstu.DeliveryServiceBackend.controllers;

public enum OrderAction {
    MAKE,
    REJECT,
    ASSEMBLER_REFUSE,
    COURIER_REFUSE,
    TO_ASSEMBLY,
    TO_ASSEMBLED,
    TO_DELIVERY,
    TO_DELIVERED
}
