package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra.config;

import java.util.Date;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class TimeZoneConfig {

    @Value("${dates.timezone}")
    String timeZone;

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        System.out.println("Date in UTC: " + new Date().toString());
    }
}
