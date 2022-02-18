package com.endterm.project.dto;

import com.endterm.project.entities.Post;
import com.endterm.project.projections.MyUserProjection;

import java.util.List;

public class NewsFeedDTO {

    private List<MyUserProjection> friends;
    private List<Post> posts;

    public NewsFeedDTO() {
    }

    public List<MyUserProjection> getFriends() {
        return friends;
    }

    public void setFriends(List<MyUserProjection> friends) {
        this.friends = friends;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
