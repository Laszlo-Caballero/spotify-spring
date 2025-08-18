package com.spotify.rest.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssingRolesDto {

    @NotEmpty(message = "Roles list cannot be empty")
    @NotEmpty
    @Size(min = 1, message = "At least one role must be assigned")
    private List<@Min(value = 0, message = "Number of tracks must be a positive integer") Integer> roles;

    @Positive
    @NotNull
    private int userId;
}
