package com.endterm.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String text;
    private Timestamp created;
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUser user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    @JsonIgnore
    private Post post;

    @OneToOne(mappedBy = "comment", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private CommentViewed commentViewed;

    public Comment() {
    }

    public CommentViewed getCommentViewed() {
        return commentViewed;
    }

    public void setCommentViewed(CommentViewed commentViewed) {
        this.commentViewed = commentViewed;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public MyUser getUser() {
        return user;
    }

    public void setUser(MyUser user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
