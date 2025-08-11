package com.spotify.rest.Model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Views.View;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "album")
@Getter
@Setter
public class Album {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.AlbumView.class, View.ArtistView.class, View.SongView.class})
    public int albumId;

    @Column
    @JsonView({View.AlbumView.class, View.ArtistView.class, View.SongView.class})
    public String NameAlbum;

    @Temporal(TemporalType.DATE)
    @JsonView({View.AlbumView.class, View.ArtistView.class, View.SongView.class})
    public Date releaseDate;


    @Column(columnDefinition = "BIT DEFAULT 1")
    @JsonView({View.AlbumView.class, View.ArtistView.class, View.SongView.class})
    public Boolean status;

    @ManyToMany
    @JoinTable(name = "album_songs",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "SongId"))
    @JsonView({View.AlbumView.class, View.ArtistView.class})
    public List<Song> songs;


    @ManyToMany
    @JoinTable(name = "album_artists",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "ArtistId"))
    @JsonView({View.AlbumView.class, View.SongView.class})
    public List<Artist> artists;

}
