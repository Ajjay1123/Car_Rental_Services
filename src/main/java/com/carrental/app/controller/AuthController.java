package com.carrental.app.controller;

import com.carrental.app.dto.AuthResponse;
import com.carrental.app.dto.LoginRequest;
import com.carrental.app.dto.RegisterRequest;
import com.carrental.app.model.User;
import com.carrental.app.service.AuthService;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request, HttpSession session) {
        User user = authService.authenticate(request.getUsername(), request.getPassword());
        session.setAttribute("USER_ID", user.getId());
        session.setAttribute("ROLE", user.getRole().name());
        return AuthResponse.from(user);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse signup(@RequestBody RegisterRequest request, HttpSession session) {
        User user = authService.register(
                request.getFullName(),
                request.getUsername(),
                request.getPassword(),
                request.getEmail(),
                request.getPhone()
        );
        session.setAttribute("USER_ID", user.getId());
        session.setAttribute("ROLE", user.getRole().name());
        return AuthResponse.from(user);
    }

    @GetMapping("/me")
    public AuthResponse me(HttpSession session) {
        return AuthResponse.from(authService.getAuthenticatedUser(session));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
