package com.spotify.rest.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.spotify.rest.Model.Song;

import jakarta.validation.constraints.*;
@Getter
@Setter
public class SongDto {
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title can only contain alphanumeric characters and spaces")
    @NotBlank(message = "Title cannot be blank")
    private String title;


    @NotNull(message = "Artists cannot be null")
    @NotEmpty(message = "Artists cannot be empty")
    public List<@Min(value = 0, message = "Number of tracks must be a positive integer") Integer> albums;

    public Song builderSong(){
        Song song = new Song();
        song.setTitle(this.title);
        song.setViews(0);
        song.setStatus(true);
        return song;
    }
}
