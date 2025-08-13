package com.spotify.rest.Dto;

import com.spotify.rest.Model.Artist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    @NotBlank(message = "Description cannot be blank")
    public String description;

    @NotNull(message = "Files cannot be null")
    @Positive(message = "Files must be a positive integer")
    public int coverArtist;

    @NotNull(message = "Files cannot be null")
    @Positive(message = "Files must be a positive integer")
    public int heroCover;

    public Artist builderArtist() {
        Artist artist = new Artist();
        artist.setName(this.Name);
        artist.setDescription(this.description);
        artist.setStatus(true);
        return artist;
    }

}
