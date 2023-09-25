package com.cliniquems.userservice.dto;

import com.cliniquems.userservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private List<Role> roles;
}
