package com.vorstu.DeliveryServiceBackend.services.action.processors;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderStatus;
import com.vorstu.DeliveryServiceBackend.services.action.resolver.ActionProcessor;
import org.springframework.stereotype.Component;

@Component
public class AssemblyActionProcessor implements ActionProcessor<AssemblerEntity> {
    @Override
    public void process(OrderEntity order, AssemblerEntity entity){
        order.setStatus(OrderStatus.ASSEMBLING);
        order.setAssembler(entity);
    }

    @Override
    public OrderAction getAction(){return OrderAction.TO_ASSEMBLY;}
}
