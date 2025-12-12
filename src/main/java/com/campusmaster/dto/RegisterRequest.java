package com.campusmaster.dto;

import com.campusmaster.enums.RoleName;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private RoleName role;
}
