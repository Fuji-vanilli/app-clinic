package com.cliniquems.porteservice.dto;

import com.cliniquems.porteservice.model.Porte;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PorteMapperImpl implements PorteMapper{
    @Override
    public Porte mapToPorte(PorteRequest request) {
        return Porte.builder()
                .numero(request.getNumero())
                .codeMedecin(request.getCodeMedecin())
                .speciality(request.getSpeciality())
                .build();
    }

    @Override
    public PorteResponse mapToPorteResponse(Porte porte) {
        return PorteResponse.builder()
                .id(porte.getId())
                .numero(porte.getNumero())
                .codeMedecin(porte.getCodeMedecin())
                .codePatients(porte.getCodePatients())
                .medecin(porte.getMedecin())
                .patients(porte.getPatients())
                .creationDate(porte.getCreationDate())
                .lastUpdate(porte.getLastUpdate())
                .speciality(porte.getSpeciality())
                .build();
    }
}
