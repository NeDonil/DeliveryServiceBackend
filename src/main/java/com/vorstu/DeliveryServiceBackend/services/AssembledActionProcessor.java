package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import org.springframework.stereotype.Component;

@Component()
public class AssembledActionProcessor implements ActionProcessor{
    @Override
    public void process(OrderEntity order, AssemblerEntity assembler){
        order.setStatus(OrderStatus.ASSEMBLED);
    }

    @Override
    public OrderAction getAction(){return OrderAction.TO_ASSEMBLED;}
}
