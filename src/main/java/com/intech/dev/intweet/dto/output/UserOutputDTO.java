package com.intech.dev.intweet.dto.output;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserOutputDTO {
    private String username;
    private Boolean admin;
}
