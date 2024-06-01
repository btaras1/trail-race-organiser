package com.intellexi.race_command_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.jackson2.CoreJackson2Module;
import org.springframework.web.client.RestTemplate;

import static lombok.AccessLevel.PRIVATE;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class Config {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
