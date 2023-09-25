package com.cliniquems.userservice.dto;

import com.cliniquems.userservice.model.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapperImpl implements UserMapper{
    @Override
    public User mapToUser(UserRequest request) {
        return User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .roles(request.getRoles())
                .build();
    }

    @Override
    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .roles(user.getRoles())
                .password(user.getPassword())
                .creationDate(user.getCreationDate())
                .lastUpdate(user.getLastUpdate())
                .build();
    }
}
