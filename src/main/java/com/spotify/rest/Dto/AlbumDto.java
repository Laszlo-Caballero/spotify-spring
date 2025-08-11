package com.spotify.rest.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spotify.rest.Model.Album;

@Getter
@Setter
public class AlbumDto {
    @NotNull(message = "NameAlbum cannot be null")
    @Size(min = 1, max = 100, message = "NameAlbum must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "NameAlbum can only contain alphanumeric characters and spaces")
    @NotBlank(message = "NameAlbum cannot be blank")
    public String NameAlbum;

    @NotNull(message = "Release date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date releaseDate;

    @NotNull(message = "Artists cannot be null")
    @NotEmpty(message = "Artists cannot be empty")
    public List<@Min(value = 0, message = "Number of tracks must be a positive integer") Integer> artists;

    public Album builderAlbum() {
        Album album = new Album();
        album.setNameAlbum(this.NameAlbum);
        album.setReleaseDate(this.releaseDate);
        album.setStatus(true);
        return album;
    }
}
