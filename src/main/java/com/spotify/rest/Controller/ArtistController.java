package com.spotify.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rest.Dto.ArtistDto;
import com.spotify.rest.Model.Artist;
import com.spotify.rest.Service.ArtistService;
import com.spotify.rest.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/artist")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Artist>>> getAllArtists() {
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Artist>> getArtistById(@PathVariable int id) {
        return artistService.getArtistById(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Artist>> createArtist(@RequestBody @Valid ArtistDto artist) {
        return artistService.createArtist(artist);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Artist>> updateArtist(@PathVariable int id, @RequestBody @Valid ArtistDto artistDto) {
        return artistService.updateArtist(id, artistDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteArtist(@PathVariable int id) {
        return artistService.deleteArtist(id);
    }

}
