package com.spotify.rest.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Views.View;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Song")
@Getter
@Setter
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(View.SongView.class)
    private int songId;

    @Column(name = "title")
    @JsonView(View.SongView.class)
    private String title;

    @Column
    @JsonView(View.SongView.class)
    public int views;

    @Column(columnDefinition = "BIT DEFAULT 1")
    @JsonView(View.SongView.class)
    public Boolean status;

    @ManyToMany(mappedBy = "songs")
    @JsonView(View.SongView.class)
    private List<Album> albums;
    
}
