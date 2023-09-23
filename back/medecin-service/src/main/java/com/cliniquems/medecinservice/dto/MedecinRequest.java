package com.cliniquems.medecinservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MedecinRequest {
    private String code;
    private String firstname;
    private String lastname;
    private BigDecimal salary;
    private String speciality;
    private String phoneNumber;
}
