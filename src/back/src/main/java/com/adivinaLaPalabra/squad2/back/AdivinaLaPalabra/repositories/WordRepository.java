package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.repositories;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {

    Word findByValue(String value);

}
