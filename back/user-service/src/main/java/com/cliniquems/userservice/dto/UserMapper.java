package com.cliniquems.userservice.dto;

import com.cliniquems.userservice.model.User;

public interface UserMapper {
    User mapToUser(UserRequest request);
    UserResponse mapToUserResponse(User user);
}
