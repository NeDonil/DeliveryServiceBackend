package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;

import java.util.EnumMap;

public class OrderActionMapper {
    public static EnumMap<OrderStatus, OrderAction> map;
    static {
        map = new EnumMap<>(OrderStatus.class);
        map.put(OrderStatus.PLACED, OrderAction.MAKE);
        map.put(OrderStatus.ASSEMBLING, OrderAction.TO_ASSEMBLY);
        map.put(OrderStatus.ASSEMBLED, OrderAction.TO_ASSEMBLED);
        map.put(OrderStatus.DELIVERING, OrderAction.TO_DELIVERY);
        map.put(OrderStatus.DELIVERED, OrderAction.TO_DELIVERED);
    }

    public static OrderAction getAction(OrderStatus status){
        return map.get(status);
    }
}
