package com.practice.cardcollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CardRepository cardRepository) {
        return args -> {
            logger.info("Loading data from database...");
            logger.info("Preloading " + cardRepository.save(new Card("Piplup", "Brilliant Stars", "35/172")));
            logger.info("Preloading " + cardRepository.save(new Card("Piplup", "Cosmic Eclipse", "239/236")));
        };
    }
}
