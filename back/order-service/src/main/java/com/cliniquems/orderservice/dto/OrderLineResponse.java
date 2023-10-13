package com.cliniquems.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderLineResponse {
    private String code;
    private String codeMedicament;
    private Medicament medicament;
    private BigDecimal quantity;
    private BigDecimal totalPrice;
}
