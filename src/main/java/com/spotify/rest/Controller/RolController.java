package com.spotify.rest.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Dto.AssingRolesDto;
import com.spotify.rest.Dto.RolDto;
import com.spotify.rest.Model.Rol;
import com.spotify.rest.Service.RolService;
import com.spotify.rest.Views.View;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping()
    @JsonView(View.RolView.class)
    public ResponseEntity<?> getAllRoles() {
        return rolService.getAllRoles();
    }

    @PostMapping()
    @JsonView(View.RolView.class)
    public ResponseEntity<?> addRole(@RequestBody @Valid RolDto rol) {
        return rolService.createRol(rol);
    }

    @PutMapping("/assing-roles")
    @JsonView(View.RolView.class)
    public ResponseEntity<?> setRolesToUser(@RequestBody @Valid AssingRolesDto assingRolesDto) {
        return rolService.assingRoles(assingRolesDto);
    }

    @DeleteMapping("/{id}")
    @JsonView(View.RolView.class)
    public ResponseEntity<?> updateRole(@PathVariable int id){
        return  rolService.deleteRol(id);
    }
}
