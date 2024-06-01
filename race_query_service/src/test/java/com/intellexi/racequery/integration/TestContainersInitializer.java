package com.intellexi.racequery.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_CLASS;

@Testcontainers
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = AFTER_CLASS)
@AutoConfigureWebTestClient
public abstract class TestContainersInitializer {

	@Autowired
	protected WebTestClient webTestClient;

	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest");

	@Container
	static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3"))
			.withEnv("KAFKA_CREATE_TOPICS", "races:1:3,applications:1:3");;


	@BeforeAll
	public static void beforeAll() {
		postgres.withReuse(true);
		postgres.start();
		kafka.start();
	}

	@DynamicPropertySource
	static void dynamicProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.kafka.bootstrap-servers", () -> kafka.getBootstrapServers());
		registry.add("spring.datasource.url=", () -> postgres.getJdbcUrl());
		registry.add("spring.datasource.username=", () -> postgres.getUsername());
		registry.add("spring.datasource.password=", () -> postgres.getPassword());
	}

	@AfterAll
	public static void afterAll() {
		postgres.stop();
		kafka.stop();
	}
}
