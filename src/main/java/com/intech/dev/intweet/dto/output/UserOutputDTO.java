package com.intech.dev.intweet.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
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
