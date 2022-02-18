package com.endterm.project.services;

import com.endterm.project.projections.MyUserProjection;

import java.util.List;

public interface UserRelationshipInterface {

    public List<MyUserProjection> findAllFriends(int userId);
}
