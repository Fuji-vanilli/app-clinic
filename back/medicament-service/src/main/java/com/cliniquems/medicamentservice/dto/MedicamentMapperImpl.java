package com.cliniquems.medicamentservice.dto;

import com.cliniquems.medicamentservice.model.Medicament;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicamentMapperImpl implements MedicamentMapper{

    @Override
    public Medicament mapToMedicament(MedicamentRequest request) {
        return Medicament.builder()
                .code(request.getCode())
                .price(request.getPrice())
                .designation(request.getDesignation())
                .build();
    }

    @Override
    public MedicamentResponse mapToMedicamentResponse(Medicament medicament) {
        return MedicamentResponse.builder()
                .code(medicament.getCode())
                .designation(medicament.getDesignation())
                .price(medicament.getPrice())
                .creationDate(medicament.getCreationDate())
                .lastUpdate(medicament.getLastUpdate())
                .build();
    }
}
