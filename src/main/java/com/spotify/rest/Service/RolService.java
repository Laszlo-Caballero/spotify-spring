package com.spotify.rest.Service;

import com.spotify.rest.Dto.AssingRolesDto;
import com.spotify.rest.Dto.RolDto;
import com.spotify.rest.Model.User;
import com.spotify.rest.Repository.RolRepository;
import com.spotify.rest.Repository.UserRepository;
import com.spotify.rest.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> getAllRoles() {
        var allRoles = rolRepository.findAll();

        allRoles.forEach(r -> {
            var users = r.getUsers();
            r.setUsersWithThisRole(
                    users.stream().map(
                            s -> {
                                User user = new User();
                                user.setUserId(s.getUserId());
                                user.setUsername(s.getUsername());
                                user.setEmail(s.getEmail());
                                return user;
                            }
                    ).toList()
            );
        });



        return  ResponseEntity.ok(new ApiResponse<>(200, "Roles retrieved successfully", allRoles));
    }

    public ResponseEntity<?> createRol(RolDto rolDto) {
        var rol = rolDto.builderRol();

        rolRepository.save(rol);

        return ResponseEntity.ok(new ApiResponse<>(200, "Role created successfully", rol));
    }


    public ResponseEntity<?> deleteRol(int id) {
        var rol = rolRepository.findById(id).orElse(null);

        if (rol == null) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "Role not found", null));
        }

        rol.setActive(false);

        rolRepository.save(rol);

        return  ResponseEntity.ok(new ApiResponse<>(200, "Role deleted successfully", rol));
    }

    public ResponseEntity<?> assingRoles(AssingRolesDto assingRolesDto) {
        var roles = rolRepository.findAllById(assingRolesDto.getRoles());

        if(roles.isEmpty() || roles.size() != assingRolesDto.getRoles().size()){
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "No roles found with the provided IDs", null));
        }

        var findUser = userRepository.findById(assingRolesDto.getUserId()).orElse(null);

        if(findUser == null){
            return ResponseEntity.badRequest().body(new ApiResponse<>(400, "No user found with the provided ID", null));
        }

        findUser.setRoles(roles);
        userRepository.save(findUser);

        return ResponseEntity.ok(new ApiResponse<>(200, "Roles assigned successfully", findUser));
    }
}
