package com.cliniquems.porteservice.controller;

import com.cliniquems.porteservice.Utils.Response;
import com.cliniquems.porteservice.dto.PorteRequest;
import com.cliniquems.porteservice.service.PorteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.cliniquems.porteservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class PorteApi implements PorteController{
    private final PorteService porteService;
    @Override
    public ResponseEntity<Response> add(PorteRequest request) {
        return ResponseEntity.ok(porteService.add(request));
    }

    @Override
    public ResponseEntity<Response> addPatient(Map<String, String> data) {
        return ResponseEntity.ok(porteService.addPatient(data));
    }

    @Override
    public ResponseEntity<Response> get(String numero) {
        return ResponseEntity.ok(porteService.get(numero));
    }

    @Override
    public ResponseEntity<Response> all(int page, int size) {
        return ResponseEntity.ok(porteService.all(page, size));
    }

    @Override
    public ResponseEntity<Response> update(PorteRequest request) {
        return ResponseEntity.ok(porteService.update(request));
    }

    @Override
    public ResponseEntity<Response> delete(String numero) {
        return ResponseEntity.ok(porteService.delete(numero));
    }
}
