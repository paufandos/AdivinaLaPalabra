package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.GameHistoryDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Game;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities.DateUtils;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.WordHelper.*;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.AuthHelper.*;

public class GameHelper {

    public static final String CHECK_ATTEMPTS_IN_RANGE_URL = "/checkAttemptsInRange/";

    public static final String GET_CORRECT_WORD_URL = "/getCorrectWord/";

    public static final String GET_LAST_TEN_GAMES_URL = "/getLastTenGames";

    public static final String GET_TOP3_GAMES_URL = "/getTopThreeGames";

    public static final String GET_ALL_GAMES_URL = "/getAllGames";

    public static final String NEW_GAME_URL = "/newGame";

    public static final String BAD_GAME_ID = "badGameId";

    public static final UUID GAME_ID = UUID.randomUUID();

    public static final Game GAME = new Game(GAME_ID, EXISTING_WORD_IN_THE_DICTIONARY);

    public static final List<Game> EXPECTED_GAME_LIST = createGameList(new Game(), 5);

    public static final List<Game> EXPECTED_ALL_GAME_LIST = createGameList(
            new Game(GAME_ID, EXISTING_WORD_IN_THE_DICTIONARY, DEFAULT_USER,
                    DateUtils.generateLocalDateTimeNow(), 5, false),
            12);

    public static final List<GameHistoryDTO> EXPECTED_ALL_GAME_HISTORY_LIST = createGameList(
            new GameHistoryDTO( DateUtils.generateLocalDateTimeNow(), true, EXISTENT_WORD, 3),
            12);

    public static final List<GameHistoryDTO> EXPECTED_GAME_HISTORY_LIST = createGameList(
            new GameHistoryDTO(DateUtils.generateLocalDateTimeNow(), true, EXISTENT_WORD, 5),
            5);


    public static final List<Game> EXPECTED_TOP3_GAME_LIST = createGameList(
            new Game(), 3);

    public static final List<GameHistoryDTO> EXPECTED_TOP3_GAME_HISTORY_LIST = createGameList(
            new GameHistoryDTO(DateUtils.generateLocalDateTimeNow(), true, EXISTENT_WORD, 5), 3);

    public static final String NEW_GAME_EXPECTED_DATA = """
            {
            game_id:""" + GAME_ID + """
                }
            """;

    public static <T> List<T> createGameList(T game, int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> game)
                .collect(Collectors.toList());
    }
}
