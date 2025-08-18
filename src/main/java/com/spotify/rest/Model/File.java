package com.spotify.rest.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;
    @Column
    private String fileName;

    @OneToMany(mappedBy = "file")
    @JsonIgnore
    private List<Album> albums;

    @OneToMany(mappedBy = "file")
    @JsonIgnore
    private List<Song> songs;

    @OneToMany(mappedBy = "file")
    @JsonIgnore
    private List<Artist> artists;

    @OneToMany(mappedBy = "heroFile")
    @JsonIgnore
    private List<Artist> heroArtists;

}
