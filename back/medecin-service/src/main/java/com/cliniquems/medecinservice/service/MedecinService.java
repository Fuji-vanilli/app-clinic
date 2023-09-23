package com.cliniquems.medecinservice.service;

import com.cliniquems.medecinservice.Utils.Response;
import com.cliniquems.medecinservice.dto.MedecinRequest;

public interface MedecinService {
    Response add(MedecinRequest request);
    Response get(String code);
    Response update(MedecinRequest request);
    Response all();
    Response delete(String code);
}
