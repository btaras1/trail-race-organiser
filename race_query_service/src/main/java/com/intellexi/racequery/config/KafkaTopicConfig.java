package com.intellexi.racequery.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    public static final String RACE_TOPIC_NAME = "race-event";
    public static final String APPLICATION_TOPIC_NAME = "application-event";

    @Bean
    public NewTopic raceTopic() {
        return new NewTopic(RACE_TOPIC_NAME, 3, (short) 1);
    }

    @Bean
    public NewTopic applicationTopic() {
        return new NewTopic(APPLICATION_TOPIC_NAME, 3, (short) 1);
    }
}