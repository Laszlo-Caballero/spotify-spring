package com.spotify.rest.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Song")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "songId")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int songId;

    @Column(name = "title")
    private String title;

    @Column
    public int views;

    @Column(columnDefinition = "BIT DEFAULT 1")
    public Boolean status;

    @ManyToMany
    @JoinTable(name = "album_songs",
           joinColumns = @JoinColumn(name = "SongId"),
           inverseJoinColumns = @JoinColumn(name = "AlbumId"))
    private List<Album> albums;
    
    @ManyToOne
    @JoinColumn(name = "FileId")
    private File file;
}
