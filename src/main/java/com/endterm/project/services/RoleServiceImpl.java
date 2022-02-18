package com.endterm.project.services;

import com.endterm.project.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RoleServiceImpl implements RoleServiceInterface {
    
    @Autowired
    RoleRepo roleRepo;
}
