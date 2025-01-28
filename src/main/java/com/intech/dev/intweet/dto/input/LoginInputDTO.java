package com.intech.dev.intweet.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LoginInputDTO {

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String username;
    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String password;

}
