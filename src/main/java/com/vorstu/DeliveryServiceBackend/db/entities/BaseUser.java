package com.vorstu.DeliveryServiceBackend.db.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String fio;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    private UserCredentialsEntity credentials;
}
