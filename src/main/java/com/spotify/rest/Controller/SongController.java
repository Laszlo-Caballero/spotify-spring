package com.spotify.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rest.Dto.SongDto;
import com.spotify.rest.Model.Song;
import com.spotify.rest.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/songs")
public class SongController {

 
}
