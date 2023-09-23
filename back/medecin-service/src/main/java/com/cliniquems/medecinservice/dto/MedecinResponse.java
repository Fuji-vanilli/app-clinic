package com.cliniquems.medecinservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MedecinResponse {
    private String code;
    private String firstname;
    private String lastname;
    private BigDecimal salary;
    private String speciality;
    private Date creationDate;
    private Date lastUpdate;
    private String phoneNumber;
}
