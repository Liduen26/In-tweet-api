package com.intech.dev.intweet.controller;

import com.intech.dev.intweet.annotation.IsAdmin;
import com.intech.dev.intweet.dto.output.UserProfileOutputDTO;
import com.intech.dev.intweet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public UserProfileOutputDTO getUserProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserProfile(userDetails.getUsername());
    }

    @IsAdmin
    @PostMapping("/ban/{username}")
    public void banUser(@PathVariable String username) {
        userService.banUser(username);
    }
}
