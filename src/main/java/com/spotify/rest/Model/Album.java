package com.spotify.rest.Model;

import java.sql.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Views.View;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "album")
@Getter
@Setter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.AlbumView.class, View.ArtistView.class})
    public int albumId;

    @Column
    @JsonView({View.AlbumView.class, View.ArtistView.class})
    public String nameAlbum;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonView({View.AlbumView.class, View.ArtistView.class})
    public Date releaseDate;


    @Column(columnDefinition = "BIT DEFAULT 1")
    @JsonView({View.AlbumView.class, View.ArtistView.class})
    public Boolean status;

    @ManyToMany
    @JoinTable(name = "album_songs",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "SongId"))
    @JsonView({View.AlbumView.class, View.ArtistView.class})
    public List<Song> songs;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_artists",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "ArtistId"))
    @JsonView({View.AlbumView.class, View.SongView.class})
    public List<Artist> artists;


    @ManyToOne
    @JoinColumn(name = "FileId")
    @JsonView({View.AlbumView.class, View.ArtistView.class, View.SongView.class})
    public File file;
}
