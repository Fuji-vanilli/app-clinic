package com.cliniquems.patientservice.dto;

import com.cliniquems.patientservice.model.Patient;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientMapperImpl implements PatientMapper{
    @Override
    public Patient mapToPatient(PatientRequest request) {
        return Patient.builder()
                .code(request.getCode())
                .address(request.getAddress())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dateOfBirth(request.getDateOfBirth())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    @Override
    public PatientResponse mapToPatientResponse(Patient patient) {
        return PatientResponse.builder()
                .id(patient.getId())
                .code(patient.getCode())
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .creationDate(patient.getCreationDate())
                .lastUpdateDate(patient.getLastUpdateDate())
                .status(patient.getStatus())
                .dateOfBirth(patient.getDateOfBirth())
                .phoneNumber(patient.getPhoneNumber())
                .address(patient.getAddress())
                .build();
    }
}
