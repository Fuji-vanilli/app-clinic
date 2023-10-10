package com.cliniquems.porteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PorteResponse {
    private Long id;
    private String numero;
    private String codeMedecin;
    private List<String> codePatients;
    private String speciality;
    private Medecin medecin;
    private List<Patient> patients;
    private Date creationDate;
    private Date lastUpdate;
}
