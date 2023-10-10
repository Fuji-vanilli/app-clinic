package com.cliniquems.porteservice.model;

import com.cliniquems.porteservice.dto.Medecin;
import com.cliniquems.porteservice.dto.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Porte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String codeMedecin;
    @ElementCollection
    private List<String> codePatients= new ArrayList<>();
    private String speciality;
    @Transient
    private Medecin medecin;
    @Transient
    private List<Patient> patients= new ArrayList<>();
    private Date creationDate;
    private Date lastUpdate;
}
