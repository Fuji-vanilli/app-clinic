package com.cliniquems.medicamentservice.controller;

import com.cliniquems.medicamentservice.Utils.Response;
import com.cliniquems.medicamentservice.dto.MedicamentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MedicamentController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody MedicamentRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @PatchMapping("patch")
    ResponseEntity<Response> update(@RequestBody MedicamentRequest request);
    @GetMapping("all")
    ResponseEntity<Response> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
