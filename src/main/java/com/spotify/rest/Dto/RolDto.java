package com.spotify.rest.Dto;


import com.spotify.rest.Model.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolDto {

    @NotEmpty(message = "Role name cannot be empty")
    @NotNull
    @NotBlank
    private String rolName;


    @NotEmpty
    @NotNull
    @NotBlank
    private String description;


    public Rol builderRol(){
        Rol rol = new Rol();
        rol.setRolName(this.rolName);
        rol.setDescription(this.description);
        return rol;
    }
}
