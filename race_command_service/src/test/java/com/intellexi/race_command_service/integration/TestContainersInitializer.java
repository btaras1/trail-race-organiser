package com.intellexi.race_command_service.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.junit.jupiter.Container;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
public abstract class TestContainersInitializer {

	@Autowired
	protected WebTestClient webTestClient;

	@Container
	static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3"));

	@BeforeAll
	public static void beforeAll() {
		kafka.start();
	}

	@DynamicPropertySource
	static void dynamicProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", () -> kafka.getBootstrapServers());
	}

	@AfterAll
	public static void afterAll() {
		kafka.stop();
	}

}
