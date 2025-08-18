package com.spotify.rest.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Dto.AssingRolesDto;
import com.spotify.rest.Dto.RolDto;
import com.spotify.rest.Model.Rol;
import com.spotify.rest.Service.RolService;
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
    public ResponseEntity<?> getAllRoles() {
        return rolService.getAllRoles();
    }

    @PostMapping()
    public ResponseEntity<?> addRole(@RequestBody @Valid RolDto rol) {
        return rolService.createRol(rol);
    }

    @PutMapping("/assing-roles")
    public ResponseEntity<?> setRolesToUser(@RequestBody @Valid AssingRolesDto assingRolesDto) {
        return rolService.assingRoles(assingRolesDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateRole(@PathVariable int id){
        return  rolService.deleteRol(id);
    }
}
