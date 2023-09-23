package com.cliniquems.medecinservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "medecin")
public class Medecin {
    private String id;
    private String code;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private BigDecimal salary;
    private String speciality;
    private Date creationDate;
    private Date lastUpdate;
}
