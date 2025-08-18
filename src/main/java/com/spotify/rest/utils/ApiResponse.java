package com.spotify.rest.utils;

import com.fasterxml.jackson.annotation.JsonView;

import com.spotify.rest.Views.View;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonView({View.SongView.class, View.AlbumView.class, View.ArtistView.class, View.FileView.class, View.RolView.class, View.UserView.class})
public class ApiResponse<T> {
    private int code;

    private String message;

    private T data;
}
