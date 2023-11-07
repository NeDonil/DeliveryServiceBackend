package com.vorstu.DeliveryServiceBackend.services.action.resolver;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;

public interface ActionProcessor<T> {

    void process(OrderEntity order, T entity);
    OrderAction getAction();

    @Autowired
    default void registerMyself(ActionResolver actionResolver){
        actionResolver.register(getAction(), this);
    }
}
