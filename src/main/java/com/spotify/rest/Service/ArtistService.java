package com.spotify.rest.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spotify.rest.Dto.ArtistDto;
import com.spotify.rest.Model.Artist;
import com.spotify.rest.Repository.ArtistRepository;
import com.spotify.rest.Repository.FileRepository;
import com.spotify.rest.utils.ApiResponse;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private FileRepository fileRepository;


    public ResponseEntity<ApiResponse<List<Artist>>> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Success", artists));
    }
    public ResponseEntity<ApiResponse<Artist>> getArtistById(int id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Artist not found", null));
        }
        return ResponseEntity.ok(new ApiResponse<>(200, "Success", artist));
    }

    public ResponseEntity<ApiResponse<Artist>> createArtist(ArtistDto artistDto) {
        var newArtist = artistDto.builderArtist();

        var coverArtist = fileRepository.findById(artistDto.getCoverArtist()).orElse(null);
        if (coverArtist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Cover artist not found", null));
        }
        newArtist.setFile(coverArtist);

        artistRepository.save(newArtist);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Artist created successfully", newArtist));
    }

    public ResponseEntity<ApiResponse<Artist>> updateArtist(int id, ArtistDto artistDto) {
        Artist existingArtist = artistRepository.findById(id).orElse(null);
        if (existingArtist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Artist not found", null));
        }
        var coverArtist = fileRepository.findById(artistDto.getCoverArtist()).orElse(null);
        if (coverArtist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Cover artist not found", null));
        }

        existingArtist.setName(artistDto.getName());
        existingArtist.setFile(coverArtist);
        existingArtist.setDescription(artistDto.getDescription());
        artistRepository.save(existingArtist);
        return ResponseEntity.ok(new ApiResponse<>(200, "Artist updated successfully", existingArtist));
    }


    public ResponseEntity<ApiResponse<Void>> deleteArtist(int id) {
        Artist existingArtist = artistRepository.findById(id).orElse(null);
        if (existingArtist == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(404, "Artist not found", null));
        }
        existingArtist.setStatus(false);
        artistRepository.save(existingArtist);
        return ResponseEntity.ok(new ApiResponse<>(200, "Artist deleted successfully", null));
    }




}
