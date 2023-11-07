package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class AssemblyActionProcessor implements ActionProcessor{
    @Override
    public void process(OrderEntity order, AssemblerEntity assembler){
        order.setStatus(OrderStatus.ASSEMBLING);
        order.setAssembler(assembler);
    }

    @Override
    public OrderAction getAction(){return OrderAction.TO_ASSEMBLY;}
}
