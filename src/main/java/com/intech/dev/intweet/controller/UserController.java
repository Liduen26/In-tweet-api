package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.dto.output.UserProfileOutputDTO;
import com.intech.dev.intweet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // TODO : Add preAuthorize ADMIN
    @PostMapping("/ban/{username}")
    public void banUser(@PathVariable String username) {
        userService.banUser(username);
    }
}
