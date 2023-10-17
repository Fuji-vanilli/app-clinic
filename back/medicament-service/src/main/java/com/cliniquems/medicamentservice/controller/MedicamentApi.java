package com.cliniquems.medicamentservice.controller;

import com.cliniquems.medicamentservice.Utils.Response;
import com.cliniquems.medicamentservice.dto.MedicamentRequest;
import com.cliniquems.medicamentservice.service.MedicamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cliniquems.medicamentservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class MedicamentApi implements MedicamentController{
    private final MedicamentService medicamentService;
    @Override
    public ResponseEntity<Response> add(MedicamentRequest request) {
        return ResponseEntity.ok(medicamentService.add(request));
    }

    @Override
    public ResponseEntity<Response> get(String code) {
        return ResponseEntity.ok(medicamentService.get(code));
    }

    @Override
    public ResponseEntity<Response> update(MedicamentRequest request) {
        return ResponseEntity.ok(medicamentService.update(request));
    }

    @Override
    public ResponseEntity<Response> all(int page, int size) {
        return ResponseEntity.ok(medicamentService.all(page, size));
    }

    @Override
    public ResponseEntity<Response> delete(String code) {
        return ResponseEntity.ok(medicamentService.delete(code));
    }
}
