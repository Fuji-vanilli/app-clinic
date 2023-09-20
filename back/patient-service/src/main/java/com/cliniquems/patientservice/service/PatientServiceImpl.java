package com.cliniquems.patientservice.service;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientRequest;
import com.cliniquems.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService{
    private final PatientRepository repository;
    @Override
    public Response add(PatientRequest request) {
        return null;
    }

    @Override
    public Response get(String code) {
        return null;
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response delete(String code) {
        return null;
    }
}
