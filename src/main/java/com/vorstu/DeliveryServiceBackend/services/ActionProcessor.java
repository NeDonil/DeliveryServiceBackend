package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

public interface ActionProcessor {

    void process(OrderEntity order, AssemblerEntity assembler);
    OrderAction getAction();

    @Autowired
    default void registerMyself(ActionResolver actionResolver){
        actionResolver.register(getAction(), this);
    }
}
