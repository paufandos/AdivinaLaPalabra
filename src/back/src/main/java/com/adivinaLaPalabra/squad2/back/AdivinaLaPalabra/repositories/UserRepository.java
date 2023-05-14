package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByName(String name);

    Boolean existsByName(String name);
}
