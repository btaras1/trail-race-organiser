package com.intellexi.race_command_service.rest.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.intellexi.race_command_service.config.GrantedAuthorityDeserializer;
import com.intellexi.race_command_service.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserDetailsResponse implements UserDetails {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dob;

    private UserRole role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
