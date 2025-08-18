package com.spotify.rest.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spotify.rest.Dto.LoginDto;
import com.spotify.rest.Dto.UserDto;
import com.spotify.rest.Repository.UserRepository;
import com.spotify.rest.utils.ApiResponse;
import com.spotify.rest.utils.JwtUtil;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncryptService encryptService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<?> register(UserDto userDto){
        String encryptedPassword = encryptService.encrypt(userDto.getPassword());

        var newUser = userDto.builderUser();

        newUser.setPassword(encryptedPassword);

        userRepository.save(newUser);

        String token = jwtUtil.generateToken(newUser);

        newUser.setToken(token);

        return ResponseEntity.ok(new ApiResponse<>(200, "User registered successfully", newUser));
    }

    public ResponseEntity<?> login(LoginDto loginDto) {

        var user = userRepository.findByEmail(loginDto.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).body(new ApiResponse<>(401, "Invalid email or password", null));
        }

        var isPasswordValid = encryptService.checkPassword(loginDto.getPassword(), user.getPassword());

        if (!isPasswordValid) {
            return ResponseEntity.status(401).body(new ApiResponse<>(401, "Invalid email or password", null));
        }

        String token = jwtUtil.generateToken(user);

        user.setToken(token);

        return ResponseEntity.ok(new ApiResponse<>(200, "User logged in successfully", user));
    }
}
