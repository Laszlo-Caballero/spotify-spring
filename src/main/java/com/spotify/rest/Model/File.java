package com.spotify.rest.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Views.View;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.FileView.class, View.AlbumView.class, View.SongView.class, View.ArtistView.class})
    private int fileId;
    @Column
    @JsonView({View.FileView.class, View.AlbumView.class, View.SongView.class, View.ArtistView.class})
    private String fileName;

    @OneToMany(mappedBy = "file")
    @JsonView(View.FileView.class)
    private List<Album> albums;

    @OneToMany(mappedBy = "file")
    @JsonView(View.FileView.class)
    private List<Song> songs;

    @OneToMany(mappedBy = "file")
    @JsonView(View.FileView.class)
    private List<Artist> artists;

    @OneToMany(mappedBy = "heroFile")
    @JsonView(View.FileView.class)
    private List<Artist> heroArtists;

}
