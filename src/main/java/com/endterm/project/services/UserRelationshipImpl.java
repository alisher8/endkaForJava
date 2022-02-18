package com.endterm.project.services;

import com.endterm.project.projections.MyUserProjection;
import com.endterm.project.repos.UserRelationshipRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRelationshipImpl implements UserRelationshipInterface {

    @Autowired
    UserRelationshipRepo userRelationshipRepo;

    public List<MyUserProjection> findAllFriends(int userId) {
        return userRelationshipRepo.getAllFriendsWithUnion(userId,userId);
    }
}
