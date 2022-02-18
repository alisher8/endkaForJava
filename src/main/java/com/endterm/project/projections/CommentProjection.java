package com.endterm.project.projections;

import java.sql.Timestamp;

public interface CommentProjection {
    String getCommentId();
    String getText();
    Timestamp getCreated();
    MyUserProjection getUser();
}
