package com.vorstu.DeliveryServiceBackend.services.action.processors;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.BaseUser;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RejectActionProcessor implements ActionProcessor<BaseUser> {
    @Override
    public void process(OrderEntity order, BaseUser entity){
        order.setStatus(OrderStatus.REJECTED);
        order.setEndDate(LocalDateTime.now());
    }

    @Override
    public OrderAction getAction(){return OrderAction.REJECT;}
}
