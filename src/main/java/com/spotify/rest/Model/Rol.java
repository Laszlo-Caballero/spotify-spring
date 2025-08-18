package com.spotify.rest.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rolId;

    @Column()
    private String rolName;

    @Column()
    private String description;

    @Column(columnDefinition = "BIT ")
    private boolean isActive = true;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
}
