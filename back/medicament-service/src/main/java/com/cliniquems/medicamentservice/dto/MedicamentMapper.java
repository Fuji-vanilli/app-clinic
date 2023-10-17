package com.cliniquems.medicamentservice.dto;

import com.cliniquems.medicamentservice.model.Medicament;

public interface MedicamentMapper {
    Medicament mapToMedicament(MedicamentRequest request);
    MedicamentResponse mapToMedicamentResponse(Medicament medicament);
}
