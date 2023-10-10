package com.cliniquems.porteservice.service;

import com.cliniquems.porteservice.Utils.Response;
import com.cliniquems.porteservice.dto.PorteRequest;

import java.util.Map;

public interface PorteService {
    Response add(PorteRequest request);
    Response addPatient(Map<String, String> data);
    Response get(String numero);
    Response all(int page, int size);
    Response update(PorteRequest request);
    Response delete(String numero);
}
