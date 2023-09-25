package com.cliniquems.userservice.validator;


import com.cliniquems.userservice.dto.UserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserValidator {

    public static List<String> validate(UserRequest request){
        List<String> errors= new ArrayList<>();

        if(Objects.isNull(request.getEmail())){
            errors.add("email required!!!");
        }
        if(Objects.isNull(request.getPassword())){
            errors.add("password required!");
        }
        if(Objects.isNull(request.getRoles())){
            errors.add("roles required!");
        }
        if(Objects.isNull(request.getUsername())){
            errors.add("username required!");
        }
        return errors;
    }
}
