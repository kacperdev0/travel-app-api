package com.kacper.travelApp.config;

import org.apache.catalina.startup.WebappServiceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
    @Bean
    public WebClient.Builder webClientBean() {
        return WebClient.builder()
                .baseUrl("https://overpass-api.de/api/");
    }
}
