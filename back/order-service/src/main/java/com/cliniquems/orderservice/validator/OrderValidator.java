package com.cliniquems.orderservice.validator;

import com.cliniquems.orderservice.dto.OrderLineRequest;
import com.cliniquems.orderservice.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderValidator {
    public static List<String> validate(Order request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getCode())){
            errors.add("code required!!!");
        }
        if(Objects.isNull(request.getCodeOrderLines())){
            errors.add("code orderLine required!");
        }
        return errors;
    }
}
