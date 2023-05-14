package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import static com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.helpers.UtilsHelper.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UtilsTest {

    @Test
    void generateLocalDateNowTest() {
        LocalDate now = LocalDate.now();
        assertEquals(now, DateUtils.generateLocalDateNow());
    }

    @Test
    void generateLocalDateTimeNowTest() {
        assertInstanceOf(LocalDateTime.class, DateUtils.generateLocalDateTimeNow());
    }

    @Test
    void generateZonedDateTimeNowTest() {
        assertInstanceOf(ZonedDateTime.class, DateUtils.generateZonedDateTime());
    }

    @Test
    void decodeBase64String() {
        assertEquals(Base64Utils.decode(ENCODED_STRING), DECODED_STRING);
    }

    @Test
    void encodeBase64String() {
        assertEquals(Base64Utils.encode(DECODED_STRING), ENCODED_STRING);
    }
}
