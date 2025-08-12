package com.spotify.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Model.File;
import com.spotify.rest.Service.FileService;
import com.spotify.rest.Views.View;
import com.spotify.rest.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping
    @JsonView(View.FileView.class)
    public ResponseEntity<ApiResponse<List<File>>> getAllFiles() {
        return fileService.getAllFiles();
    }

    @PostMapping
    @JsonView(View.FileView.class)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.createFile(file);
    }

}
