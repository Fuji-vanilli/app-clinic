package com.cliniquems.medecinservice.dto;

import com.cliniquems.medecinservice.model.Medecin;

public interface MedecinMapper {
    Medecin mapToMedecin(MedecinRequest request);
    MedecinResponse mapToMedecinResponse(Medecin medecin);
}
