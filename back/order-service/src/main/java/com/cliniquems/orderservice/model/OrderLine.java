package com.cliniquems.orderservice.model;

import com.cliniquems.orderservice.dto.Medicament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderLine {
    private String id;
    private String code;
    private List<String> codeMedicaments;
    private List<Medicament> medicaments;
    private BigDecimal totalPrice;
}

