package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto;

import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GameDTO(@JsonProperty("game_id") UUID gameId) {
}
