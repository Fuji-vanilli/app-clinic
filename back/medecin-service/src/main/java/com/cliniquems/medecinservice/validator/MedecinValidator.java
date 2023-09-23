package com.cliniquems.medecinservice.validator;


import com.cliniquems.medecinservice.dto.MedecinRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedecinValidator {

    public static List<String> validate(MedecinRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getFirstname())){
            errors.add("firstname required!!!");
        }
        if(Objects.isNull(request.getCode())){
            errors.add("code required!");
        }
        if(Objects.isNull(request.getSpeciality())){
            errors.add("speciality required!");
        }
        if(Objects.isNull(request.getPhoneNumber())){
            errors.add("phone number required!");
        }
        if(Objects.isNull(request.getSalary())){
            errors.add("salary required!");
        }
        return errors;
    }
}
