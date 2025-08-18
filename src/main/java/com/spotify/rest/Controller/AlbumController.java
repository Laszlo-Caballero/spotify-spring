package com.spotify.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Dto.AlbumDto;
import com.spotify.rest.Model.Album;
import com.spotify.rest.Service.AlbumService;
import com.spotify.rest.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Album>>> getAllAlbums(@RequestParam(defaultValue = "") String artistName) {
        return albumService.getAllAlbums(artistName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Album>> getAlbumById(@PathVariable int id) {
        return albumService.getAlbumById(id);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Album>> createAlbum(@RequestBody @Valid AlbumDto album) {
        return albumService.createAlbum(album);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Album>> updateAlbum(@PathVariable int id, @RequestBody @Valid AlbumDto album) {
        return albumService.updateAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAlbum(@PathVariable int id) {
        return albumService.deleteAlbum(id);
    }
}
