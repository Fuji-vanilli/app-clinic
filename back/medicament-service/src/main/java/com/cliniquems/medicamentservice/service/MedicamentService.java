package com.cliniquems.medicamentservice.service;

import com.cliniquems.medicamentservice.Utils.Response;
import com.cliniquems.medicamentservice.dto.MedicamentRequest;

public interface MedicamentService {
    Response add(MedicamentRequest request);
    Response get(String code);
    Response update(MedicamentRequest request);
    Response all(int page, int size);
    Response delete(String code);
}
