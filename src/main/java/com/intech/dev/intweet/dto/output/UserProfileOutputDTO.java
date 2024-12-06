package com.intech.dev.intweet.dto.output;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserProfileOutputDTO {
    private String username;
    private Boolean admin;
    private long totalLikes;
}
