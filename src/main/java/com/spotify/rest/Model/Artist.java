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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Artist")
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.ArtistView.class, View.AlbumView.class, View.SongView.class})
    public int artistId;

    @Column(name = "name")
    @JsonView({View.ArtistView.class, View.AlbumView.class, View.SongView.class})
    private String Name;

    @Column(columnDefinition = "TEXT")
    @JsonView({View.ArtistView.class, View.AlbumView.class, View.SongView.class})
    public String description;


    @Column(columnDefinition = "BIT DEFAULT 1")
    @JsonView({View.ArtistView.class, View.AlbumView.class, View.SongView.class})
    public Boolean status;

    @ManyToMany(mappedBy = "artists")
    @JsonView(View.ArtistView.class)
    private List<Album> albums;
    @ManyToOne
    @JsonView(View.ArtistView.class)
    private File file;
}
