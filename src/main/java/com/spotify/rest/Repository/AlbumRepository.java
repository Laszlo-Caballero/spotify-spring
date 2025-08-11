package com.spotify.rest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rest.Model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
    List<Album> findAllByAlbumIdIn(List<Integer> ids);
}
