package com.cliniquems.patientservice.dto;

import com.cliniquems.patientservice.model.Address;
import com.cliniquems.patientservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PatientRequest {
    private String code;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private Status status;
    private String phoneNumber;
    private Address address;
}
