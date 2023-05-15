package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto;

import java.time.LocalDateTime;

public record GameHistoryDTO(LocalDateTime date, Boolean winned, String correctWord, int attempts) {
}