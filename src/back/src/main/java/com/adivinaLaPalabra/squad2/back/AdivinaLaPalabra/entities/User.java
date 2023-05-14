package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities;

import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Nonnull
    @Column(name = "name", unique = true)
    private String name;

    @Nonnull
    @Column(name = "password")
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

}
