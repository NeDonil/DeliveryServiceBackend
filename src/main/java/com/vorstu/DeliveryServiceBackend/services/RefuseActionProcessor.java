package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;

public class RefuseActionProcessor implements ActionProcessor{
    @Override
    public void process(OrderEntity order, AssemblerEntity assembler){
        order.setStatus(OrderStatus.PLACED);
        order.setAssembler(null);
    }

    @Override
    public OrderAction getAction(){return OrderAction.REFUSE;}
}
