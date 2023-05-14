package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {

    public List<Game> findTop10ByUser_IdAndAttemptsGreaterThanOrderByDateDesc(UUID userId,int attempts);

    public List<Game> findTop3ByUser_IdAndWinnedTrueAndAttemptsGreaterThanOrderByAttemptsAsc(UUID userId,int attempts);

    public List<Game> findAllByUser_IdAndAttemptsGreaterThanOrderByDateDesc(UUID userId,int attempts);

    public long countByUser_IdAndAttemptsGreaterThan(UUID userId,int attempts);
}

