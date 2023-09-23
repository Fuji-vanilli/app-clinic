package com.cliniquems.medecinservice.controller;

import com.cliniquems.medecinservice.Utils.Response;
import com.cliniquems.medecinservice.dto.MedecinRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MedecinController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody MedecinRequest request);
    @PutMapping("update")
    ResponseEntity<Response> update(@RequestBody MedecinRequest request);
    @GetMapping("get/{code}")
    ResponseEntity<Response> get(@PathVariable String code);
    @GetMapping("all")
    ResponseEntity<Response> all(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    );
    @DeleteMapping("delete/{code}")
    ResponseEntity<Response> delete(@PathVariable String code);
}
