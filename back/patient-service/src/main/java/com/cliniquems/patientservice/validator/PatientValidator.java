package com.cliniquems.patientservice.validator;


import com.cliniquems.patientservice.dto.PatientRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PatientValidator {

    public static List<String> validate(PatientRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getFirstname())){
            errors.add("firstname required!!!");
        }
        if(Objects.isNull(request.getCode())){
            errors.add("code required!");
        }
        if(Objects.isNull(request.getAddress())){
            errors.add("address required!");
        }
        if(Objects.isNull(request.getPhoneNumber())){
            errors.add("phone number required!");
        }
        if(Objects.isNull(request.getDateOfBirth())){
            errors.add("date of birth required!");
        }
        return errors;
    }
}
