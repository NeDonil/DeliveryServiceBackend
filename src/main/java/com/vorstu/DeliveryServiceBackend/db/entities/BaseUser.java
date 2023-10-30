package com.vorstu.DeliveryServiceBackend.db.entities;

import com.vorstu.DeliveryServiceBackend.db.entities.auth.UserCredentialsEntity;
import com.vorstu.DeliveryServiceBackend.db.entities.auth.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fio;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "credentials_id", referencedColumnName = "id")
    private UserCredentialsEntity credentials;

    public BaseUser(String fio, String email, String password, UserRole role){
        this.fio = fio;
        credentials = new UserCredentialsEntity(email, password, role);
    }
}
