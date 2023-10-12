package com.cliniquems.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Order {
    private String id;
    private String code;
    private List<String> codeOrderLines;
    private List<OrderLine> orderLines;
    private BigDecimal totalPrice;
    private Date date;
}
