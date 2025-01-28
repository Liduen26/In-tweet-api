package com.intech.dev.intweet.unit.controller;

import com.intech.dev.intweet.controller.AuthController;
import com.intech.dev.intweet.dto.input.LoginInputDTO;
import com.intech.dev.intweet.dto.input.SignUpInputDTO;
import com.intech.dev.intweet.entity.User;
import com.intech.dev.intweet.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private AuthController controller;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AuthenticationManager manager;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenLoginWithBadPassword_thenReturn401() {
        // Given
        LoginInputDTO loginInputDTO = LoginInputDTO.builder()
                .username("test")
                .password("testpsw")
                .build();
        BindingResult bindingResult = new BeanPropertyBindingResult(loginInputDTO, "login");

        // When
        when(manager.authenticate(any()))
                .thenThrow( new AuthenticationCredentialsNotFoundException("bad password") );

        // Then
        Assertions.assertEquals(
                ResponseEntity.status(401).body("Le nom d'utilisateur ou le mot de passe n'est pas bon"),
                controller.login(loginInputDTO, bindingResult)
        );
    }

    @Test
    public void whenLoginGoodUserPassword_thenReturn200() {
        // Given
        LoginInputDTO loginInputDTO = LoginInputDTO.builder()
                .username("test")
                .password("testpsw")
                .build();
        BindingResult bindingResult = new BeanPropertyBindingResult(loginInputDTO, "login");

        // When
        when(manager.authenticate(any())).thenReturn(null);

        // Then
        Assertions.assertEquals(
                ResponseEntity.status(200).body(anyString()).getStatusCode(),
                controller.login(loginInputDTO, bindingResult).getStatusCode()
        );
    }

    @Test
    public void whenSignUpUserAlreadyExist_thenReturnError() {
        // Given
        SignUpInputDTO signUpInputDTO = SignUpInputDTO.builder()
                .username("test")
                .password("testpsw")
                .build();
        BindingResult bindingResult = new BeanPropertyBindingResult(signUpInputDTO, "login");

        // When
        when(userRepository.findByUsername(anyString()))
                .thenReturn(Optional.of(User.builder().build()));

        // Then
        Assertions.assertEquals(
                ResponseEntity.status(400).body("L'utilisateur test existe déjà"),
                controller.signUp(signUpInputDTO, bindingResult)
        );
    }

    @Test
    public void whenSignUp_thenReturn200() {
        // Given
        SignUpInputDTO signUpInputDTO = SignUpInputDTO.builder()
                .username("test")
                .password("testpsw")
                .build();
        BindingResult bindingResult = new BeanPropertyBindingResult(signUpInputDTO, "login");

        // When
        when(userRepository.findByUsername(anyString()))
                .thenReturn(Optional.empty());
        when(passwordEncoder.encode(anyString()))
                .thenReturn("psw");
        when(userRepository.save(any()))
                .thenReturn(null);

        // Then
        Assertions.assertEquals(
                ResponseEntity.status(200).body(anyString()).getStatusCode(),
                controller.signUp(signUpInputDTO, bindingResult).getStatusCode()
        );
    }
}
