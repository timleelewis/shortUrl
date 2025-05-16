package com.example.C.Users.lewis.Documents.shorturl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Semaphore;

@Configuration
public class config {
     @Bean
    public Semaphore semaphore(@Value("${shorturl.concurrent.limit:10}") int limit) {
        return new Semaphore(limit);
    }
}
