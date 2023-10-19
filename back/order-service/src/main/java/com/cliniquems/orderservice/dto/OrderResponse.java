package com.cliniquems.orderservice.dto;

import com.cliniquems.orderservice.model.OrderLine;
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
public class OrderResponse {
    private String code;
    private List<String> codeOrderLines;
    private List<OrderLine> orderLines;
    private BigDecimal totalPrice;
    private Date date;
}
