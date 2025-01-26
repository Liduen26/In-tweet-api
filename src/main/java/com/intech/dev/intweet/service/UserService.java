package com.intech.dev.intweet.service;

import com.intech.dev.intweet.dto.output.UserProfileOutputDTO;
import com.intech.dev.intweet.entity.Ban;
import com.intech.dev.intweet.entity.User;
import com.intech.dev.intweet.mapper.UserMapper;
import com.intech.dev.intweet.repository.BanRepository;
import com.intech.dev.intweet.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BanRepository banRepository;
    private final UserMapper userMapper;

    public UserProfileOutputDTO getUserProfile(String username) {
        return userRepository.findByUsername(username)
                .map( user -> {
                    boolean isBanned =  banRepository.isUserBanned(user.getId());
                    UserProfileOutputDTO dto = userMapper.entityToProfileDto(user);
                    dto.setBanned(isBanned);
                    return dto;
                })
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public void banUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        banRepository.save(
                Ban.builder().user(user).build()
        );
    }

}
