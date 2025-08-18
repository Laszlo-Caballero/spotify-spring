package com.spotify.rest.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.rest.Dto.LoginDto;
import com.spotify.rest.Dto.UserDto;
import com.spotify.rest.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDto userDto) {
        try {
            return userService.register(userDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Username or Email al ready register", null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        return userService.login(loginDto);
    }

}
