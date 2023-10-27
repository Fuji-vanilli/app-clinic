package com.cliniquems.gatewayservice.config;


import com.nimbusds.jwt.JWTClaimNames;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter authoritiesConverter;
    private final JwtAuthConfig authConfig;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        final List<GrantedAuthority> authorities = Stream.concat(
                Objects.requireNonNull(authoritiesConverter.convert(jwt)).stream(), extractRoles(jwt).stream()
        ).toList();

        return new JwtAuthenticationToken(jwt, authorities, getClaims(jwt));
    }
    public String getClaims(Jwt jwt) {
        String claimName= JWTClaimNames.SUBJECT;
        if(authConfig.getResourceId()!= null)
            claimName= authConfig.getResourceId();
        return claimName;
    }

    public Collection<? extends SimpleGrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> accessResource= jwt.getClaims();
        Map<String, Object> resource;
        Collection<String> resourceRole;

        if(Objects.isNull(accessResource)
            || (resource= (Map<String, Object>) accessResource.get(authConfig.getResourceId()))== null
            || (resourceRole= (Collection<String>) resource.get("role"))== null) {
            return Set.of();
        }
        return resourceRole.stream()
                .map(r-> new SimpleGrantedAuthority("ROLE_"+r))
                .toList();
    }
}
