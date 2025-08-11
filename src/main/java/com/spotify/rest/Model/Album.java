package com.spotify.rest.Model;

import java.sql.Date;
import java.util.List;

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
    public int AlbumId;

    @Column
    public String NameAlbum;

    @Temporal(TemporalType.DATE)
    public Date releaseDate;


    @Column(columnDefinition = "boolean default true")
    public Boolean status;

    @ManyToMany
    @JoinTable(name = "album_songs",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "SongId"))
    public List<Song> songs;


    @ManyToMany
    @JoinTable(name = "album_artists",
               joinColumns = @JoinColumn(name = "AlbumId"),
               inverseJoinColumns = @JoinColumn(name = "ArtistId"))
    public List<Artist> artists;

}
