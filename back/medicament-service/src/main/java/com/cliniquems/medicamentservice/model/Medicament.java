package com.cliniquems.medicamentservice.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Medicament {
    private Long id;
    private String code;
    private String designation;
    private BigDecimal price;
    private Date creationDate;
    private Date lastUpdate;
}
