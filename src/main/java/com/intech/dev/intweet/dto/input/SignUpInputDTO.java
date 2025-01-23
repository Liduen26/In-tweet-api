package com.intech.dev.intweet.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class SignUpInputDTO {

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;
    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8, message = "Le mot de passe doit faire au moins 8 caractères")
    private String password;

}
