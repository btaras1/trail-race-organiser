package com.intellexi.racequery.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellexi.racequery.event.strategy.EventStrategy;
import com.intellexi.racequery.exception.EventTypeUnknownException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Service
@Slf4j
@FieldDefaults(level = PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventConsumer {

    public static final String RACE_TOPIC_NAME = "race-event";
    public static final String APPLICATION_TOPIC_NAME = "application-event";

    List<EventStrategy> eventStrategies;
    ObjectMapper mapper;

    @KafkaListener(topicPartitions = { @TopicPartition(
            topic = RACE_TOPIC_NAME,
            partitions = "0")
    })
    public void consumeRaceEvents(String message) throws Exception {
        log.info("Received Race event -> {}", message);
        RaceEvent event = mapper.readValue(message, RaceEvent.class);

        eventStrategies.stream()
                .filter(eventStrategy -> eventStrategy.isSatisfied(event))
                .findFirst()
                .orElseThrow(() ->
                        new EventTypeUnknownException(String.format("Event type is unknown for request: %s", message)))
                .handleEvent(event);

        log.info("Race event successfully processed -> entity-id: {}, type: {}", event.getRequest().getId(), event.getEventType());
    }

    @KafkaListener(
            topicPartitions = { @TopicPartition(
                    topic = APPLICATION_TOPIC_NAME,
                    partitions = "0")
            })
    public void consumeApplicationEvents(String message) throws Exception {
        log.info("Received Application event -> {}", message);
        ApplicationEvent event = mapper.readValue(message, ApplicationEvent.class);

        eventStrategies.stream()
                .filter(eventStrategy -> eventStrategy.isSatisfied(event))
                .findFirst()
                .orElseThrow(() ->
                        new EventTypeUnknownException(String.format("Event type is unknown for request: %s", message)))
                .handleEvent(event);

        log.info("Application event successfully processed -> entity-id: {}, type: {}", event.getRequest().getId(), event.getEventType());
    }
}
