package com.spotify.rest.Dto;

import com.spotify.rest.Model.Artist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDto {


    @NotNull(message = "Artist cannot be null")
    @Size(min = 1, max = 100, message = "Artist must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Artist can only contain alphanumeric characters and spaces")
    @NotBlank(message = "Artist cannot be blank")
    public String Name;


    @NotNull(message = "Description cannot be null")
    @Size(min = 1, max = 500, message = "Description must be between 1 and 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 .,?!]+$", message = "Description can only contain alphanumeric characters, spaces, and punctuation")
    @NotBlank(message = "Description cannot be blank")
    public String description;

    public Artist builderArtist() {
        Artist artist = new Artist();
        artist.setName(this.Name);
        artist.setDescription(this.description);
        artist.setStatus(true);
        return artist;
    }

}
