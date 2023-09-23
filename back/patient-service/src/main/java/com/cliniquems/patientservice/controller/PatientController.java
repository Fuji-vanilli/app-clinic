package com.cliniquems.patientservice.controller;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PatientController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody PatientRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all(int page, int size);
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
