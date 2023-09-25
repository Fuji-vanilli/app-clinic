package com.cliniquems.userservice.dto;

import com.cliniquems.userservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserResponse {
    private String username;
    private String email;
    private String password;
    private List<Role> roles;
    private Date creationDate;
    private Date lastUpdate;
}
