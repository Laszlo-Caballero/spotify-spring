package com.spotify.rest.Service;


import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spotify.rest.Model.Song;
import com.spotify.rest.Repository.FileRepository;
import com.spotify.rest.utils.ApiResponse;

@Service
public class FileService {
    private static final String FILE_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    @Autowired
    private FileRepository fileRepository;


    public ResponseEntity<?> createFile(MultipartFile file){
        String[] fileSplit = file.getOriginalFilename().split("\\.");


        String fileName = new StringBuilder().append(fileSplit[0]).append(System.currentTimeMillis()).append(".").append(fileSplit[1]).toString();

        File directory = new File(FILE_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            file.transferTo(new File(FILE_DIRECTORY + fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ApiResponse<>(500, "File upload failed", null));
        }

        var fileEntity = new com.spotify.rest.Model.File();
        fileEntity.setFileName(fileName);
        fileRepository.save(fileEntity);
        return ResponseEntity.ok(new ApiResponse<>(200, "File uploaded successfully", fileEntity));
    }

    public ResponseEntity<ApiResponse<List<com.spotify.rest.Model.File>>> getAllFiles(){
        var files = fileRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>(200, "Files retrieved successfully", files));
    }


}
