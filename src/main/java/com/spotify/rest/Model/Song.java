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
    private int SongId;

    @Column(name = "title")
    private String Title;

    @Column
    public int views;

    @Column(columnDefinition = "BIT DEFAULT 1")
    public Boolean status;

    @ManyToMany(mappedBy = "songs")
    @JsonView(View.SongView.class)
    private List<Album> albums;
    
}
