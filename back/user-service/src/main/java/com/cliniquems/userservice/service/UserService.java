package com.cliniquems.userservice.service;

import com.cliniquems.userservice.Utils.Response;
import com.cliniquems.userservice.dto.UserRequest;

public interface UserService {
    Response add(UserRequest request);
    Response update(UserRequest request);
    Response get(String email);
    Response all(int page, int size);
    Response delete(String email);
}
