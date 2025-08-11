package com.spotify.rest.utils;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Views.View;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    @JsonView({View.ArtistView.class, View.AlbumView.class})
    private int code;

    @JsonView({View.ArtistView.class, View.AlbumView.class})
    private String message;
    
    @JsonView({View.ArtistView.class, View.AlbumView.class})
    private T data;
}
