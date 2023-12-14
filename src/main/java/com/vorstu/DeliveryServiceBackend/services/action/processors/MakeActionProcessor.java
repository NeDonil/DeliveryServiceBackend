package com.vorstu.DeliveryServiceBackend.services.action.processors;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.CustomerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionProcessor;
import org.springframework.stereotype.Component;

@Component
public class MakeActionProcessor implements ActionProcessor<CustomerEntity> {
    @Override
    public void process(OrderEntity order, CustomerEntity entity){
        order.setStatus(OrderStatus.PLACED);
    }

    @Override
    public OrderAction getAction(){return OrderAction.MAKE;}
}
