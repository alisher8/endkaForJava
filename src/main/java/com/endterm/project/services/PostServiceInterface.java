package com.endterm.project.services;

import com.endterm.project.entities.Cheer;
import com.endterm.project.entities.MyUser;
import com.endterm.project.entities.Post;
import com.endterm.project.entities.PostImage;
import com.endterm.project.projections.PostProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostServiceInterface {

    public List<Post> findByUser(Integer userId);

    public List<Post> findByUserIds(List<Integer> userIds);

    public Post findPostByPostId(int postId);

    public void updatePost(Post post);

    public void insertPost(Post post);

    public void insertPostWithImage(String text, MultipartFile file, MyUser user);

    public PostImage findPostImageByPostId(int postId);

    public List<Cheer> cheers(Post post, MyUser user);

    public List<PostProjection> findByUserIdsTEST();

    public void removePost(Post post);

    public int getAllPosts();

    public Post getSpecificPostFromCommentId(Integer commentId) throws NullPointerException;

    public List<Post> findByUserIdsAndByPage(List<Integer> userIds, Integer page);
}
