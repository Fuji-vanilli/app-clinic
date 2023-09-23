package com.cliniquems.medecinservice.controller;

import com.cliniquems.medecinservice.Utils.Response;
import com.cliniquems.medecinservice.dto.MedecinRequest;
import org.springframework.http.ResponseEntity;

public interface MedecinController {
    ResponseEntity<Response> add(MedecinRequest request);
    ResponseEntity<Response> update(MedecinRequest request);
    ResponseEntity<Response> get(String code);
    ResponseEntity<Response> all();
    ResponseEntity<Response> delete(String code);
}
