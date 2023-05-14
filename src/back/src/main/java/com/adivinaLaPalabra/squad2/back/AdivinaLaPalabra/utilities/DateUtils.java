package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class DateUtils {

    public static LocalDate generateLocalDateNow() {
        return LocalDate.now();
    }

    public static ZonedDateTime generateZonedDateTime() {
        return ZonedDateTime.now();
    }

    public static LocalDateTime generateLocalDateTimeNow() {
        return LocalDateTime.now();
    }
}
