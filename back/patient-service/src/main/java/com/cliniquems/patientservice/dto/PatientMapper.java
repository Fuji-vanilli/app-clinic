package com.cliniquems.patientservice.dto;

import com.cliniquems.patientservice.model.Patient;

public interface PatientMapper {
    Patient mapToPatient(PatientRequest request);
    PatientResponse mapToPatientResponse(Patient patient);
}
