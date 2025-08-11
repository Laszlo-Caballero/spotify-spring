package com.spotify.rest.Dto;

import lombok.Data;
import jakarta.validation.constraints.*;
@Data
public class SongDto {
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title can only contain alphanumeric characters and spaces")
    @NotBlank(message = "Title cannot be blank")
    private String title;
}
