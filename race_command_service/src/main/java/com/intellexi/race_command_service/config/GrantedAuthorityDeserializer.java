package com.intellexi.race_command_service.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

public class GrantedAuthorityDeserializer extends StdDeserializer<GrantedAuthority> {

    public GrantedAuthorityDeserializer() {
        this(null);
    }

    public GrantedAuthorityDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public GrantedAuthority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String authority = node.get("authorities").asText();

        return new SimpleGrantedAuthority(node.get("authorities").get(0).findValues("authority").get(0).asText());
    }
}
