package com.spotify.rest.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.rest.Model.File;

public interface FileRepository extends JpaRepository<File, Integer> {

}
