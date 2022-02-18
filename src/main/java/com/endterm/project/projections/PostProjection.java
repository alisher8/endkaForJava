package com.endterm.project.projections;

import java.sql.Timestamp;
import java.util.List;

public interface PostProjection {
    Integer getPostId();
    String getText();
    Timestamp getCreated();
    String getImageUrl();
    MyUserProjection getUser();
    List<CommentProjection> getComments();
    List<CheerProjection> getCheers();
}
