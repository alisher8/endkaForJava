package com.endterm.project.repos;

import com.endterm.project.entities.CommentViewed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentViewedRepo extends JpaRepository<CommentViewed, Integer> {
}
