package com.cliniquems.porteservice.dto;

import com.cliniquems.porteservice.model.Porte;

public interface PorteMapper {
    Porte mapToPorte(PorteRequest request);
    PorteResponse mapToPorteResponse(Porte porte);
}
