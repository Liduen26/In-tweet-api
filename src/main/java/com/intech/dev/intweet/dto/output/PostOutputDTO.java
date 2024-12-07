package com.intech.dev.intweet.dto.output;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PostOutputDTO {
    private Integer id;
    private String title;
    private String body;
    private UserOutputDTO user;
    private String status;
    private LocalDateTime createdAt;
    @Builder.Default
    private long likes = 0;
}
