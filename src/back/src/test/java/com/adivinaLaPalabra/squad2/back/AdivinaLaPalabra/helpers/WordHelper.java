package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers;

import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.dto.LetterDTO;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.entities.Word;
import com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.request.ValidatePositionsRequest;
import java.util.List;

public class WordHelper {

    public static final String VALIDATE_POSITIONS_URL = "/validatePositions/";

    public static final String CHECK_IF_WORD_EXISTS_URL = "/checkIfWordExists/word";

    public static final ValidatePositionsRequest POSITIONS_REQUEST = new ValidatePositionsRequest('a', 'b', 'a', 'c', 'a');

    public static final String EXISTENT_WORD = "abaca";
    public static final Word EXISTING_WORD_IN_THE_DICTIONARY = new Word(1, EXISTENT_WORD);

    public static final String REQUEST_WORD = "halla";
    public static final Word REQUEST_WORD_IN_THE_DICTIONARY = new Word(1, REQUEST_WORD);

    public static final String NONEXISTENT_WORD = "aaaaa";

    public static final String EXPECTED_LIST_DATA = """
                [
                {
                   letter: h,
                   status: NOT_MATCHED,
                   position: 0
                },
               {
                    letter: a,
                    status: MATCHED,
                    position: 1
                },
                {
                    letter: l,
                    status: NOT_MATCHED,
                    position: 2
                },
                {
                    letter: l,
                    status: NOT_MATCHED,
                    position: 3
                },
                {
                    letter:a,
                    status: MATCHED,
                    position: 4
                }
            ]
            """;

    public static List<LetterDTO> LETTERDTO_LIST = List.of(
            new LetterDTO('a', LetterDTO.Status.MATCHED,0),
            new LetterDTO('b', LetterDTO.Status.MATCHED,1),
            new LetterDTO('a', LetterDTO.Status.MATCHED,2),
            new LetterDTO('c', LetterDTO.Status.MATCHED,3),
            new LetterDTO('a', LetterDTO.Status.MATCHED,4)
    );


}
