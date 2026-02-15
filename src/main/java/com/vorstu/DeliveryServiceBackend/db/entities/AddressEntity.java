package com.vorstu.DeliveryServiceBackend.db.entities;

import com.sun.istack.NotNull;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String value;
}
