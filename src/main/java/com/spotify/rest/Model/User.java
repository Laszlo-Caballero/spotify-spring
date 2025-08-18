package com.spotify.rest.Model;

import com.fasterxml.jackson.annotation.*;

import com.spotify.rest.Dto.RolDto;
import com.spotify.rest.Views.View;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.UserView.class, View.RolView.class})
    private int userId;

    @Column(unique = true)
    @JsonView({View.UserView.class, View.RolView.class})
    private String username;

    @Column(unique = true)
    @JsonView({View.UserView.class, View.RolView.class})
    private String email;

    @Column
    @JsonView(View.UserView.class)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    @JsonManagedReference
    @JsonView(View.UserView.class)
    private List<Rol> roles;

    private String token;

    @Override
    public Collection<? extends GrantedAuthority>  getAuthorities(){
    return  roles.stream()
            .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getRolName()))
            .toList();
    }


}
