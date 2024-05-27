package com.intellexi.race_command_service.rest.dto.response;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {


    @Override
    public String getAuthority() {
        return null;
    }
}
