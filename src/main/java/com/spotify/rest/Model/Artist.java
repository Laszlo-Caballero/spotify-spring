package com.spotify.rest.Model;

import java.util.List;

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
@Table(name = "Artist")
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ArtistId;
    @Column(name = "name")
    private String Name;

    @Column
    public String description;


    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    public Boolean status;

    @ManyToMany(mappedBy = "artists")
    private List<Album> albums;

}
