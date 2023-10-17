package com.cliniquems.medicamentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class MedicamentResponse {
    private String code;
    private String designation;
    private BigDecimal price;
    private Date creationDate;
    private Date lastUpdate;
}
