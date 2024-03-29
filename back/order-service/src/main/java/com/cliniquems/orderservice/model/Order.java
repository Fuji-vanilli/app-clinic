package com.cliniquems.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "order")
public class Order {
    @Id
    private String id;
    private String code;
    private List<String> codeOrderLines;
    private List<OrderLine> orderLines;
    private BigDecimal totalPrice;
    private Date date;
}
