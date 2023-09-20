package com.cliniquems.patientservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Address {
    private String address1;
    private String address2;
    private String city;
    private String country;
}
