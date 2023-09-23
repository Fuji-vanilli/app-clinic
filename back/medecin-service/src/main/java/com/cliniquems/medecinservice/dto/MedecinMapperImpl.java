package com.cliniquems.medecinservice.dto;

import com.cliniquems.medecinservice.model.Medecin;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedecinMapperImpl implements MedecinMapper{
    @Override
    public Medecin mapToMedecin(MedecinRequest request) {
        return Medecin.builder()
                .code(request.getCode())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .salary(request.getSalary())
                .speciality(request.getSpeciality())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    @Override
    public MedecinResponse mapToMedecinResponse(Medecin medecin) {
        return MedecinResponse.builder()
                .code(medecin.getCode())
                .firstname(medecin.getFirstname())
                .lastname(medecin.getLastname())
                .salary(medecin.getSalary())
                .speciality(medecin.getSpeciality())
                .creationDate(medecin.getCreationDate())
                .lastUpdate(medecin.getLastUpdate())
                .phoneNumber(medecin.getPhoneNumber())
                .build();
    }
}
