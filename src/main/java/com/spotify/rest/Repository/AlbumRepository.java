package com.spotify.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rest.Model.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

}
