package com.cliniquems.medicamentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MedicamentRequest {
    private String code;
    private String designation;
    private BigDecimal price;
}
