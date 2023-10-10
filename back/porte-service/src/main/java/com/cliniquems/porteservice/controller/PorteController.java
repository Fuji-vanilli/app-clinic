package com.cliniquems.porteservice.controller;

import com.cliniquems.porteservice.Utils.Response;
import com.cliniquems.porteservice.dto.PorteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface PorteController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody PorteRequest request);
    @PatchMapping("addPatient")
    ResponseEntity<Response> addPatient(@RequestBody Map<String, String> data);
    @GetMapping("get/{numero}")
    ResponseEntity<Response> get(@PathVariable String numero);
    @GetMapping("all")
    ResponseEntity<Response> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    ResponseEntity<Response> update(@RequestBody PorteRequest request);
    ResponseEntity<Response> delete(@PathVariable String numero);
}
