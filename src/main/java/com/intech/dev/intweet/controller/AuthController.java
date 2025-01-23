package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.dto.input.LoginInputDTO;
import com.intech.dev.intweet.dto.input.SignUpInputDTO;
import com.intech.dev.intweet.entity.User;
import com.intech.dev.intweet.repository.UserRepository;
import com.intech.dev.intweet.utils.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager manager;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginInputDTO input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword())
            );

            String token = JWTUtils.generateToken(input.getUsername(), UUID.randomUUID().toString());

            return ResponseEntity.ok(token);
        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Le nom d'utilisateur ou le mot de passe n'est pas bon");
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpInputDTO input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessages = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessages.append(error.getDefaultMessage()).append("\n");
            });
            return ResponseEntity.badRequest().body(errorMessages.toString());
        }

        boolean userAlreadyExisting = repository.findByUsername(input.getUsername()).isPresent();
        if (!userAlreadyExisting) {
            User user = new User();
            user.setUsername(input.getUsername());
            user.setPassword(passwordEncoder.encode(input.getPassword()));
            user.setAdmin(false);
            user.setCreatedAt(LocalDateTime.now());
            repository.save(user);

            String token = JWTUtils.generateToken(input.getUsername(), UUID.randomUUID().toString());

            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(400).body("L'utilisateur " + input.getUsername() + " existe déjà");
    }
}
