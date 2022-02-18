package com.endterm.project.repos;

import com.endterm.project.entities.Cheer;
import com.endterm.project.entities.MyUser;
import com.endterm.project.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CheerRepo extends JpaRepository<Cheer, Integer> {

    Optional<Cheer> findByUserAndPost(MyUser user, Post post);

    List<Cheer> findByPost(Post post);
}
