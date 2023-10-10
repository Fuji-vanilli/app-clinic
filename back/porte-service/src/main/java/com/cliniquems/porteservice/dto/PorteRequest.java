package com.cliniquems.porteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PorteRequest {
    private String numero;
    private String codeMedecin;
    private String speciality;
}
