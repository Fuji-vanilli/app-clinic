package com.cliniquems.medicamentservice.validator;


import com.cliniquems.medicamentservice.dto.MedicamentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MedicamentValidator {

    public static List<String> validate(MedicamentRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!!!");
        }
        if(Objects.isNull(request.getDesignation())){
            errors.add("designation required!");
        }
        if(Objects.isNull(request.getPrice())){
            errors.add("price required!");
        }
        return errors;
    }
}
