package com.cliniquems.porteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Medecin {
    private String firstname;
    private String lastname;
    private String speciality;
    private String phoneNumber;
}
