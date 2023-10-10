package com.cliniquems.porteservice.validator;


import com.cliniquems.porteservice.dto.PorteRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PorteValidator {

    public static List<String> validate(PorteRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getNumero())){
            errors.add("numero required!!!");
        }
        if(Objects.isNull(request.getCodeMedecin())){
            errors.add("code medecin required!");
        }
        return errors;
    }
}
