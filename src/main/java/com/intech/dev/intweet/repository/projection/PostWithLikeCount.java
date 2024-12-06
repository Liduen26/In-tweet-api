package com.intech.dev.intweet.repository.projection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.intech.dev.intweet.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class PostWithLikeCount {
    private final Integer id;
    private final String title;
    private final String body;
    private final String status;
    private final LocalDateTime createdAt;
    private final User user;
    private final Long likeCount;
}
