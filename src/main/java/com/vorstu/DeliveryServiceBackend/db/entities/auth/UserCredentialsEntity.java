package com.vorstu.DeliveryServiceBackend.db.entities.auth;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
@Getter
@Setter
@NoArgsConstructor
public class UserCredentialsEntity {

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private boolean enabled;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserCredentialsEntity(String email, String password, UserRole role){
        this.email = email;
        this.enabled = true;
        this.password = passwordEncoder.encode(password);
        this.role = role;
    }
}
