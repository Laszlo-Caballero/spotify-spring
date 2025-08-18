package com.spotify.rest.Dto;

import com.spotify.rest.Model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotBlank(message = "Username is mandatory")
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password cannot be null")
    @Size(min = 3, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    public User builderUser(){
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        return user;
    }


}
