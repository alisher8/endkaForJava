package com.endterm.project.repos;

import com.endterm.project.projections.PostProjection;
import com.endterm.project.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUserUserIdOrderByCreatedDesc(Integer userId);

    List<Post> findByUserUserIdInOrderByCreatedDesc(List<Integer> userIds);

    List<PostProjection> findAllByUserUserIdIn(List<Integer> postIds);

    @Query("SELECT COUNT(p.postId) FROM Post p")
    public int getLengthOfPosts();

    Optional<Post> findByCommentsCommentId(Integer commentId);

    List<Post> findByUserUserIdInOrderByCreatedDesc(List<Integer> userIds, Pageable pageable);
}
