package com.spotify.rest.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @Email
    @NotBlank(message = "Email is mandatory")
    @NotNull
    private String email;

    @NotBlank(message = "Password is mandatory")
    @NotNull
    private String password;
}
