package com.cliniquems.patientservice.service;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientRequest;

public interface PatientService {
    Response add(PatientRequest request);
    Response get(String code);
    Response all();
    Response delete(String code);
}
