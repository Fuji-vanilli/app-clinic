package com.cliniquems.medecinservice.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Response {
    private LocalDateTime timeStamp;
    private HttpStatus status;
    private int statusCode;
    private Map<?, ?> data;
    private URI location;
    private String message;
}
