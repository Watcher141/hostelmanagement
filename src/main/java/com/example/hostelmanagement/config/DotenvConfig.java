package com.example.hostelmanagement.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DotenvConfig {

    private static final Logger logger = LoggerFactory.getLogger(DotenvConfig.class);

    public static void load() {
        try {
            Dotenv dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

            dotenv.entries().forEach(entry -> {
                String key = entry.getKey();
                String value = entry.getValue();
                // Only set if not already present
                if (System.getProperty(key) == null && System.getenv(key) == null) {
                    System.setProperty(key, value);
                }
            });

            logger.info("Loaded {} entries from .env", dotenv.entries().size());
        } catch (Exception e) {
            logger.warn("Could not load .env file: {}", e.getMessage());
        }
    }
}
