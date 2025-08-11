package com.spotify.rest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rest.Model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    List<Artist> findAllByIdIn(List<Integer> ids);
}
