package com.spotify.rest.Model;

import java.util.List;

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
    public int artistId;

    @Column(name = "name")
    private String name;

    @Column(columnDefinition = "TEXT")
    public String description;


    @Column(columnDefinition = "BIT DEFAULT 1")
    public Boolean status;


    @ManyToMany(mappedBy = "artists")
    private List<Album> albums;

    @ManyToOne
    private File file;

    @ManyToOne
    private File heroFile;

}
