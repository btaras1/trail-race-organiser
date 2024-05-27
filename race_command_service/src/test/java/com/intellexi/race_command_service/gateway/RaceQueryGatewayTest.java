package com.intellexi.race_command_service.gateway;

import com.intellexi.race_command_service.rest.dto.response.UserDetailsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpMethod.GET;

@ExtendWith(MockitoExtension.class)
class RaceQueryGatewayTest {

    static final String ENDPOINT_URL = "https://endpoint.com";
    static final String USERNAME = "username";
    static final String AUTH_HEADER = "auth_header";
    @Mock
    UserDetailsResponse user;
    @Mock
    RestTemplate restTemplate;

    RaceQueryGateway gateway;

    @BeforeEach
    void setUp() {
        gateway = new RaceQueryGateway(ENDPOINT_URL, restTemplate);
    }

    @Test
    void authUser() {
        given(restTemplate.exchange(buildUri(), GET, buildEntity(), UserDetailsResponse.class))
                .willReturn(ResponseEntity.ok(user));

        gateway.authUser(USERNAME, AUTH_HEADER);

        verify(restTemplate, times(1))
                .exchange(buildUri(), GET, buildEntity(), UserDetailsResponse.class);
    }

    private URI buildUri() {
        return UriComponentsBuilder
                .fromUriString(ENDPOINT_URL)
                .buildAndExpand(USERNAME)
                .toUri();
    }
    private HttpEntity<String> buildEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AUTH_HEADER);

        return new HttpEntity<>("body", httpHeaders);
    }
}