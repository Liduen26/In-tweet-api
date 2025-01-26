package com.intech.dev.intweet.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PostInputDTO {

    @NotBlank(message = "Le contenu du body ne peut pas être vide")
    private String body;

}
