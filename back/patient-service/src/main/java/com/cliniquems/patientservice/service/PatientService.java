package com.cliniquems.patientservice.service;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientRequest;

import java.util.Map;

public interface PatientService {
    Response add(PatientRequest request);
    Response get(String code);
    Response all(int page, int size);
    Response delete(String code);
    Response updateStatus(Map<String, String> updateStatus);
}
