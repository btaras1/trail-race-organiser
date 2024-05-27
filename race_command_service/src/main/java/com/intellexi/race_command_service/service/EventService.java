package com.intellexi.race_command_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellexi.race_command_service.rest.dto.event.ApplicationEvent;
import com.intellexi.race_command_service.rest.dto.event.RaceEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventService {

    public static final String RACE_TOPIC_NAME = "race-event";
    public static final String APPLICATION_TOPIC_NAME = "application-event";

    KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;

    @SneakyThrows
    public void sendRaceEvent(RaceEvent event) {
        var message = objectMapper.writeValueAsString(event);

        log.info("Sending Race event -> {}", message);

        kafkaTemplate.send(MessageBuilder.withPayload(message)
                .setHeader(TOPIC, RACE_TOPIC_NAME)
                .build());
    }

    @SneakyThrows
    public void sendApplicationEvent(ApplicationEvent event) {
        var message = objectMapper.writeValueAsString(event);

        log.info("Sending Application event -> {}", message);

        kafkaTemplate.send(MessageBuilder.withPayload(message)
                .setHeader(TOPIC, APPLICATION_TOPIC_NAME)
                .build());
    }
}
