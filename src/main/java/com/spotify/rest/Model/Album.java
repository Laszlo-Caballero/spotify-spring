package com.spotify.rest.Model;

import java.sql.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    public int albumId;

    @Column
    public String nameAlbum;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date releaseDate;


    @Column(columnDefinition = "BIT DEFAULT 1")
    public Boolean status;

    @ManyToMany
    @JoinTable(name = "album_songs",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "SongId"))
    public List<Song> songs;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_artists",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "ArtistId"))
    public List<Artist> artists;


    @ManyToOne
    @JoinColumn(name = "FileId")
    public File file;
}
