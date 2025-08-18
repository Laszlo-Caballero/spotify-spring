package com.spotify.rest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rest.Model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
}
