package com.intech.dev.intweet.service;

import com.intech.dev.intweet.dto.output.UserProfileOutputDTO;
import com.intech.dev.intweet.entity.User;
import com.intech.dev.intweet.mapper.UserMapper;
import com.intech.dev.intweet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserProfileOutputDTO getUserProfile(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::entityToProfileDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
