package com.endterm.project.repos;

import com.endterm.project.entities.ImageBackground;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageBackgroundRepo extends JpaRepository<ImageBackground, Integer> {

    Optional<ImageBackground> findByTitleIgnoreCase(String title);
}
