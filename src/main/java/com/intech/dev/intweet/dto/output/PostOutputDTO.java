package com.intech.dev.intweet.dto.output;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.intech.dev.intweet.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PostOutputDTO {
    private String title;
    private String body;
    private UserOutputDTO user;
    private String status;
    private LocalDateTime createdAt;
    @Builder.Default
    private long likes = 0;
}
