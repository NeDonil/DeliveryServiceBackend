package com.vorstu.DeliveryServiceBackend.services.action.processors;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DeliveredActionProcessor implements ActionProcessor<CourierEntity> {
    @Override
    public void process(OrderEntity order, CourierEntity entity){
        order.setStatus(OrderStatus.DELIVERED);
        order.setCourier(entity);
        order.setEndDate(LocalDateTime.now());
    }

    @Override
    public OrderAction getAction(){return OrderAction.TO_DELIVERED;}
}
