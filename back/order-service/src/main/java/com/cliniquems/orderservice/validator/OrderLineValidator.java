package com.cliniquems.orderservice.validator;


import com.cliniquems.orderservice.dto.OrderLineRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderLineValidator {

    public static List<String> validate(OrderLineRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!!!");
        }
        if(Objects.isNull(request.getCodeMedicaments())){
            errors.add("code medicaments required!");
        }
        return errors;
    }
}
