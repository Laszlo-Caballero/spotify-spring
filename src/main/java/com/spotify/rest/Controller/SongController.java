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

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Dto.SongDto;
import com.spotify.rest.Model.Song;
import com.spotify.rest.Service.SongService;
import com.spotify.rest.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/songs")
public class SongController {


    @Autowired
    private SongService songService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Song>>> getAllSongs(){
        return songService.getAllSongs();
     }

     @GetMapping("/{id}")
     public ResponseEntity<ApiResponse<Song>> getSongById(@PathVariable int id){
        return songService.getSongById(id);
     }

     @PostMapping
     public ResponseEntity<ApiResponse<Song>> createSong(@RequestBody @Valid SongDto songDto){
        return songService.createSong(songDto);
     }

     @PutMapping("/{id}")
     public ResponseEntity<ApiResponse<Song>> updateSong(@PathVariable int id, @RequestBody @Valid SongDto songDto){
        return songService.updateSong(id, songDto);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<ApiResponse<Void>> deleteSong(@PathVariable int id){
        return songService.deleteSong(id);
     }
}
