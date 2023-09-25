package com.cliniquems.userservice.userservice.dto;

import com.cliniquems.userservice.userservice.model.User;

public interface UserMapper {
    User mapToUser(UserRequest request);
    UserResponse mapToUserResponse(User user);
}
