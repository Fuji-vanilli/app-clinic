package com.cliniquems.patientservice.controller;

import com.cliniquems.patientservice.Utils.Response;
import com.cliniquems.patientservice.dto.PatientRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface PatientController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody PatientRequest request);
    @PatchMapping("updateStatus")
    ResponseEntity<Response> updateStatus(@RequestBody Map<String, String> updateStatus);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size);
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
