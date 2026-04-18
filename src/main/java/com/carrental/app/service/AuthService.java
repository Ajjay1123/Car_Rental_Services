package com.carrental.app.service;

import com.carrental.app.model.User;
import com.carrental.app.model.Role;
import com.carrental.app.repository.UserRepository;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));
    }

    public User register(String fullName, String username, String password, String email, String phone) {
        requireText(fullName, "Full name is required");
        requireText(username, "Username is required");
        requireText(password, "Password is required");
        requireText(email, "Email is required");
        requireText(phone, "Phone number is required");
        if (username.length() < 4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username must be at least 4 characters");
        }
        if (password.length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password must be at least 6 characters");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter a valid email address");
        }
        if (!phone.matches("[0-9]{10}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Enter a valid 10 digit phone number");
        }
        if (userRepository.findByUsername(username.trim()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        User user = new User();
        user.setFullName(fullName.trim());
        user.setUsername(username.trim());
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(Role.USER);
        user.setEmail(email.trim());
        user.setPhone(phone.trim());
        return userRepository.save(user);
    }

    public User getAuthenticatedUser(HttpSession session) {
        Object userId = session.getAttribute("USER_ID");
        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Please login first");
        }
        return userRepository.findById(Long.valueOf(String.valueOf(userId)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User session expired"));
    }

    private void requireText(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }
}
