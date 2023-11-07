package com.vorstu.DeliveryServiceBackend.services.action.resolver;

import com.vorstu.DeliveryServiceBackend.controllers.OrderAction;
import com.vorstu.DeliveryServiceBackend.db.entities.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActionResolver<T> {
    private Map<OrderAction, ActionProcessor> actionProcessors;

    public ActionResolver(){
        actionProcessors = new HashMap<>();
    }
    public void register(OrderAction action, ActionProcessor processor){
        actionProcessors.put(action, processor);
    }

    public void resolve(OrderAction action, OrderEntity order, T entity){
        ActionProcessor processor = actionProcessors.get(action);
        if(processor != null){
            processor.process(order, entity);
        }
    }

}
