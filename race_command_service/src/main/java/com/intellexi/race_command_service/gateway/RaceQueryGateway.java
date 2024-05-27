package com.intellexi.race_command_service.gateway;

import com.intellexi.race_command_service.rest.dto.response.UserDetailsResponse;
import lombok.experimental.FieldDefaults;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpMethod.GET;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class RaceQueryGateway {

    String endpointUrl;
    RestTemplate restTemplate;

    public RaceQueryGateway(
            @Value("${services.query.endpoint}") String endpointUrl,
            RestTemplate restTemplate) {
        this.endpointUrl = endpointUrl;
        this.restTemplate = restTemplate;
    }

    public UserDetails authUser(String username, String authHeader) {
        return restTemplate.exchange(buildUri(username), GET, buildEntity(authHeader), UserDetailsResponse.class).getBody();
    }

    private URI buildUri(String username) {
        return UriComponentsBuilder
                .fromUriString(endpointUrl)
                .buildAndExpand(username)
                .toUri();
    }
    private HttpEntity<String> buildEntity(String authHeader) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authHeader);

        return new HttpEntity<>("body", httpHeaders);
    }
}
