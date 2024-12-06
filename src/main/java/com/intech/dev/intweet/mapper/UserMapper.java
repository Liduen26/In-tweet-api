package com.intech.dev.intweet.mapper;

import com.intech.dev.intweet.dto.output.UserOutputDTO;
import com.intech.dev.intweet.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserOutputDTO entityToOutputDto(User user) {
        return UserOutputDTO.builder()
                .username(user.getUsername())
                .admin(user.getAdmin())
                .build();
    }
}
