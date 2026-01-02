package demo.campusmaster_backend.presentation.auth;

import demo.campusmaster_backend.application.auth.AuthResponse;
import demo.campusmaster_backend.application.auth.LoginRequest;
import demo.campusmaster_backend.application.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // front Next.js
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
