package com.example.demo.controller;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.UtilisateurDto;
import com.example.demo.exception.EmptyException;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UtilisateurDto> registerUtilisateur(@RequestBody @Valid UtilisateurDto user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new EmptyException("Username cannot be null or empty.");
        }
        if (user.getMotDePasse() == null || user.getMotDePasse().trim().isEmpty()) {
            throw new EmptyException("Password cannot be null or empty.");
        }

        UtilisateurDto registeredUtilisateur = userService.registerUtilisateur(user);
        return new ResponseEntity<>(registeredUtilisateur, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto loginRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getMotDePasse())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity.ok("User logged in successfully: " + authentication.getName());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("User logged out successfully");
    }

}





