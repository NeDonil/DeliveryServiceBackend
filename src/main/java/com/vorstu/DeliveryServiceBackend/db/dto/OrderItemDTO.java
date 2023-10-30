package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.OrderItemEntity;
import lombok.Value;

public enum OrderItemDTO{;

    private interface Id{Long getId(); }

    private interface Count{Long getCount();}
    private interface ProductShort{ProductDTO.Short.Response getProduct();}

    public enum Short{;
        @Value
        public static class Response implements Id, Count, ProductShort {
            Long id;
            Long count;
            ProductDTO.Short.Response product;

            public static Response fromEntity(OrderItemEntity entity) {
                return new Response(entity.getId(), entity.getCount(),
                        ProductDTO.Short.Response.fromEntity(entity.getProduct()));
            }
        }
    }
}