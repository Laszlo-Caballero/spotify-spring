package com.spotify.rest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spotify.rest.Dto.AlbumDto;
import com.spotify.rest.Model.Album;
import com.spotify.rest.Repository.AlbumRepository;
import com.spotify.rest.Repository.ArtistRepository;
import com.spotify.rest.Repository.FileRepository;
import com.spotify.rest.utils.ApiResponse;
import com.spotify.rest.utils.JpaSpecification;
import com.spotify.rest.utils.SearchCriteria;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private FileRepository fileRepository;


    public ResponseEntity<ApiResponse<List<Album>>> getAllAlbums(String artistName) {

        JpaSpecification<Album> spec = new JpaSpecification<>();
        if(!artistName.isEmpty()){
            spec.setCriteria(new SearchCriteria("artists.name", ":", artistName));
        }
        var albums = albumRepository.findAll(spec);
        return ResponseEntity.ok(new ApiResponse<>(200, "Albums retrieved successfully", albums));
    }


    public ResponseEntity<ApiResponse<Album>> getAlbumById(int id){
        var findAlbum = albumRepository.findById(id).orElse(null);
        if(findAlbum == null){
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "Album not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Album retrieved successfully", findAlbum));
    }

    public ResponseEntity<ApiResponse<Album>> createAlbum(AlbumDto albumDto) {
        var newAlbum = albumDto.builderAlbum();

        var findArtist = artistRepository.findAllByArtistIdIn(albumDto.artists);

        if(findArtist.isEmpty() || findArtist.size() != albumDto.artists.size()){
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Artist not found", null));
        }

        var findCover = fileRepository.findById(albumDto.getCoverFile()).orElse(null);
        if(findCover == null){
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Cover file not found", null));
        }

        newAlbum.setArtists(findArtist);
        newAlbum.setFile(findCover);
        albumRepository.save(newAlbum);
        return ResponseEntity.ok(new ApiResponse<>(200, "Album created successfully", newAlbum));
    }


    public ResponseEntity<ApiResponse<Album>> updateAlbum(int id, AlbumDto albumDto) {

        var findAlbum = albumRepository.findById(id).orElse(null);

        if(findAlbum == null){
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "Album not found", null));
        }

        var findArtist = artistRepository.findAllByArtistIdIn(albumDto.artists);

        if(findArtist.isEmpty() || findArtist.size() != albumDto.artists.size()){
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Artist not found", null));
        }


        var findCover = fileRepository.findById(albumDto.getCoverFile()).orElse(null);
        if(findCover == null){
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Cover file not found", null));
        }

        findAlbum.setNameAlbum(albumDto.getNameAlbum());
        findAlbum.setReleaseDate(albumDto.getReleaseDate());
        findAlbum.setArtists(findArtist);
        findAlbum.setFile(findCover);
        albumRepository.save(findAlbum);
        return ResponseEntity.ok(new ApiResponse<>(200, "Album updated successfully", findAlbum));
    }

    public ResponseEntity<ApiResponse<Void>> deleteAlbum(int id) {
        var findAlbum = albumRepository.findById(id).orElse(null);
        if(findAlbum == null){
            return ResponseEntity.status(404).body(new ApiResponse<>(404, "Album not found", null));
        }
        findAlbum.setStatus(false);
        albumRepository.save(findAlbum);
        return ResponseEntity.ok(new ApiResponse<>(200, "Album deleted successfully", null));
    }
}
