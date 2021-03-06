package com.endterm.project.repos;

import com.endterm.project.entities.Comment;
import com.endterm.project.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    public List<Comment> findAllByPostUserAndUserNotAndCommentViewedViewedFalseOrderByCreatedAsc(MyUser user, MyUser user1);

    @Query("SELECT COUNT(c.commentId) FROM Comment c")
    public int getLengthOfComments();
}
