package com.cliniquems.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderLineRequest {
    private String code;
    private String codeMedicament;
    private BigDecimal quantity;
}
