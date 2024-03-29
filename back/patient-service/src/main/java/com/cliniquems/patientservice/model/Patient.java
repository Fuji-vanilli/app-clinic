package com.cliniquems.patientservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Document(value = "patient")
public class Patient {
    @Id
    private String id;
    private String code;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private Status status;
    private String phoneNumber;
    private Address address;
    private Date creationDate;
    private Date lastUpdateDate;
}
