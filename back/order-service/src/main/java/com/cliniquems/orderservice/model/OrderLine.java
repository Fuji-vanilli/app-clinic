package com.cliniquems.orderservice.model;

import com.cliniquems.orderservice.dto.Medicament;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "orderLine")
public class OrderLine {
    @Id
    private String id;
    private String code;
    private String codeMedicament;
    private Medicament medicament;
    private BigDecimal totalPrice;
    private BigDecimal quantity;
}

