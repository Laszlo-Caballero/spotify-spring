package com.spotify.rest.Repository;

import com.spotify.rest.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    public List <Rol> findAllByRolIdIn(List<Integer> ids);
}
