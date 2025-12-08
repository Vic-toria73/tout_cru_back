package com.toutcru.toutcru;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToutcruApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing() // Ne pas planter si .env absent (utile en prod)
                .load();

        // Injecter les variables dans les propriétés système
        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
        SpringApplication.run(ToutcruApplication.class, args);
	}

}
