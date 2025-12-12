package com.campusmaster.service;

import com.campusmaster.dto.RegisterRequest;
import com.campusmaster.entity.User;

public interface UserService {
    User register(RegisterRequest request);
}
