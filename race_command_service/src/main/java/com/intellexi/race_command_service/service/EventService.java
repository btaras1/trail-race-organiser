package com.intellexi.race_command_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellexi.race_command_service.rest.dto.event.ApplicationEvent;
import com.intellexi.race_command_service.rest.dto.event.RaceEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventService {

    KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;

    @SneakyThrows
    public void sendRaceEvent(RaceEvent event) {
        var message = objectMapper.writeValueAsString(event);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("races", null, message);
        log.info("Sending Race event -> {}", message);

        kafkaTemplate.send(record);
    }

    @SneakyThrows
    public void sendApplicationEvent(ApplicationEvent event) {
        var message = objectMapper.writeValueAsString(event);

        ProducerRecord<String, String> record =
                new ProducerRecord<>("applications", null, message);

        log.info("Sending Application event -> {}", message);

        kafkaTemplate.send(record);
    }
}
