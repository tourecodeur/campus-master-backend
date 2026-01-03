package com.campusmaster.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthentificationResponse {

    private String token;
    private String role;
}
