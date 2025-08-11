package com.spotify.rest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spotify.rest.Dto.SongDto;
import com.spotify.rest.Model.Song;
import com.spotify.rest.Repository.AlbumRepository;
import com.spotify.rest.Repository.SongRepository;
import com.spotify.rest.utils.ApiResponse;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private AlbumRepository albumRepository;


    public ResponseEntity<ApiResponse<List<Song>>> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Songs retrieved successfully", songs));
    }

    public ResponseEntity<ApiResponse<Song>> getSongById(int id){
        var findSong = songRepository.findById(id).orElse(null);

        if(findSong == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(404, "Not found Song", null));
        }

        return ResponseEntity.ok(new ApiResponse<>(200, "Song found", findSong));
    }


    public ResponseEntity<ApiResponse<Song>> createSong(SongDto songDto){
        Song newSong = songDto.builderSong();

        var findAlbums = albumRepository.findAllByAlbumIdIn(songDto.getAlbums());

        if(findAlbums.isEmpty() || findAlbums.size() != songDto.getAlbums().size()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "Bad request", null));
        }

        newSong.setAlbums(findAlbums);
        songRepository.save(newSong);

        return ResponseEntity.ok(new ApiResponse<>(200, "Song create", newSong));
    }


    public ResponseEntity<ApiResponse<Song>> updateSong(int id, SongDto songDto){
        Song findSong = songRepository.findById(id).orElse(null);

        if(findSong == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "Song not found", null));
        }

        var findAlbums = albumRepository.findAllByAlbumIdIn(songDto.getAlbums());


        if(findAlbums.isEmpty() || findAlbums.size() != songDto.getAlbums().size()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "Bad request", null));
        }

        findSong.setTitle(songDto.getTitle());
        findSong.setAlbums(findAlbums);
        songRepository.save(findSong);

        return ResponseEntity.ok(new ApiResponse<>(200, "Song update",findSong));
    }

    public ResponseEntity<ApiResponse<Void>> deleteSong(int id){
        Song findSong = songRepository.findById(id).orElse(null);

        if(findSong == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(400, "Song not found", null));
        }
        findSong.setStatus(false);
        songRepository.save(findSong);
        return ResponseEntity.ok(new ApiResponse<>(200, "Song delete", null));
    }
}
