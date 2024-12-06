package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.dto.output.UserProfileOutputDTO;
import com.intech.dev.intweet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileOutputDTO getUserProfile() {
        // TODO : Remplacer le nom d'utilisateur par le nom de l'authent
        return userService.getUserProfile("Liduen");
    }
}
