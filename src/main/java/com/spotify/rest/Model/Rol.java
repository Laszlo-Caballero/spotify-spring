package com.spotify.rest.Model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.spotify.rest.Views.View;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rol")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({View.RolView.class})
    private int rolId;

    @Column()
    @JsonView({View.RolView.class})
    private String rolName;

    @Column()
    @JsonView({View.RolView.class})
    private String description;

    @Column(columnDefinition = "BIT 1")
    @JsonView({View.RolView.class})
    private boolean isActive = true;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    @JsonView(View.RolView.class)
    private List<User> users;

    @JsonView({View.RolView.class})
    private List<User> usersWithThisRole;
}
