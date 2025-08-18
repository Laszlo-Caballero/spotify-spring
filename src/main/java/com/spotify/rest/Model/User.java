package com.spotify.rest.Model;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private List<Rol> roles = new ArrayList<>();


    private String token;

    @Override
    public Collection<? extends GrantedAuthority>  getAuthorities(){
    return  roles.stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getRolName()))
            .toList();
    }
}
