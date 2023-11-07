package com.vorstu.DeliveryServiceBackend.services;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.AssemblerEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActionResolver {
    private Map<OrderAction, ActionProcessor> actionProcessors;

    public ActionResolver(){
        actionProcessors = new HashMap<>();
    }
    public void register(OrderAction action, ActionProcessor processor){
        actionProcessors.put(action, processor);
    }

    public void resolve(OrderAction action, OrderEntity order, AssemblerEntity assembler){
        ActionProcessor processor = actionProcessors.get(action);
        if(processor != null){
            processor.process(order, assembler);
        }
    }

}
