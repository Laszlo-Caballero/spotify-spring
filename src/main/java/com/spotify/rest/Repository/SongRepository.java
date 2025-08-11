package com.spotify.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rest.Model.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {

}
