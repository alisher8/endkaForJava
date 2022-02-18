package com.endterm.project.repos;

import com.endterm.project.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {

     Optional<Image> findByTitleIgnoreCase(String title);
}
