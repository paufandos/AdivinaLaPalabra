package com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.adivinaLaPalabra.squad2.back.AdivinaLaPalabra")
public class AdivinaLaPalabraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdivinaLaPalabraApplication.class, args);
	}
}
