package com.cliniquems.medecinservice.controller;

import com.cliniquems.medecinservice.Utils.Response;
import com.cliniquems.medecinservice.dto.MedecinRequest;
import com.cliniquems.medecinservice.service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cliniquems.medecinservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class MedecinApi implements MedecinController{
    private final MedecinService medecinService;
    @Override
    public ResponseEntity<Response> add(MedecinRequest request) {
        return ResponseEntity.ok(medecinService.add(request));
    }

    @Override
    public ResponseEntity<Response> update(MedecinRequest request) {
        return ResponseEntity.ok(medecinService.update(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(medecinService.get(code));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(medecinService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(medecinService.delete(code));
    }
}
