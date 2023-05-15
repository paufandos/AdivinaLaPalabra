package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto;

import java.util.List;

public record LastTenGamesResponseDTO(Boolean hasEnoughGames, List<GameHistoryDTO> games) {
}
