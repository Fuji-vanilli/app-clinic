package com.cliniquems.gatewayservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Data
@Validated
@ConfigurationProperties(prefix = "jwt.auth.converter")
public class JwtAuthConfig {
    private String resourceId;
    private String principalAttribute;
}
