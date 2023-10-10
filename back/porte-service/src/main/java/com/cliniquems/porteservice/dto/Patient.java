package com.cliniquems.porteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Patient {
    private String code;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private Status status;
    private String phoneNumber;
}
