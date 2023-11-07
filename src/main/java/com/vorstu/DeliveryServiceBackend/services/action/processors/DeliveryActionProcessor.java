package com.vorstu.DeliveryServiceBackend.services.action.processors;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.CourierEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionProcessor;
import org.springframework.stereotype.Component;

@Component
public class DeliveryActionProcessor implements ActionProcessor<CourierEntity> {
    @Override
    public void process(OrderEntity order, CourierEntity entity){
        order.setStatus(OrderStatus.DELIVERING);
        order.setCourier(entity);
    }

    @Override
    public OrderAction getAction(){return OrderAction.TO_DELIVERY;}
}
