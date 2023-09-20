package com.cliniquems.patientservice.controller;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientRequest;
import com.cliniquems.patientservice.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cliniquems.patientservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class PatientApi implements PatientController{
    private final PatientService patientService;
    @Override
    public ResponseEntity<Response> add(PatientRequest request) {
        return ResponseEntity.ok(patientService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(patientService.get(code));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(patientService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(patientService.delete(code));
    }
}
