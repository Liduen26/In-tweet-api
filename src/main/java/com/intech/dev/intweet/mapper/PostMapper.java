package com.intech.dev.intweet.mapper;

import com.intech.dev.intweet.dto.output.PostOutputDTO;
import com.intech.dev.intweet.entity.Post;
import com.intech.dev.intweet.repository.projection.PostWithLikeCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;

    public PostOutputDTO entityToOutputDTO(Post post) {
        return PostOutputDTO.builder()
                .title(post.getTitle())
                .body(post.getBody())
                .user(userMapper.entityToOutputDto(post.getUser()))
                .status(post.getStatus())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public PostOutputDTO entityWithLikesToOutputDTO(PostWithLikeCount post) {
        return PostOutputDTO.builder()
                .id(post.id())
                .title(post.title())
                .body(post.body())
                .user(userMapper.entityToOutputDto(post.user()))
                .status(post.status())
                .createdAt(post.createdAt())
                .likes(post.likeCount())
                .build();
    }
}
