package com.vorstu.DeliveryServiceBackend.db.dto;

import com.vorstu.DeliveryServiceBackend.db.entities.ProductEntity;
import lombok.Value;

public enum ProductDTO{
    ;

    private interface Id{Long getId(); }
    private interface Title{String getTitle(); }
    private interface Photo{String getPhoto();}
    private interface Description{String getDescription();}
    private interface Count{Long getCount();}
    private interface Price{Long getPrice();}

    public enum Full {;
        @Value public static class Response implements Id, Title, Photo, Description, Count, Price {
            Long id;
            String title;
            String photo;
            String description;
            Long count;
            Long price;

            public static Response fromEntity(ProductEntity entity) {
                return new Response(entity.getId(), entity.getTitle(), entity.getPhoto(),
                        entity.getDescription(), entity.getCount(), entity.getPrice());
            }
        }
    }

    public enum Short {;
        @Value public static class Response implements Id, Title, Count, Price {
            Long id;
            String title;
            Long count;
            Long price;

            public static Response fromEntity(ProductEntity entity) {
                return new Response(entity.getId(), entity.getTitle(), entity.getCount(), entity.getPrice());
            }
        }
    }
}

