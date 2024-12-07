package com.intech.dev.intweet.repository.projection;

import com.intech.dev.intweet.entity.User;

import java.time.LocalDateTime;

public record PostWithLikeCount(
        Integer id,
        String title,
        String body,
        String status,
        LocalDateTime createdAt,
        User user,
        Long likeCount
)
{ }
