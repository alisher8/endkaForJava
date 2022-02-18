package com.endterm.project.controllers;

import com.endterm.project.dto.SearchImageDto;
import com.endterm.project.dto.UserDto;
import com.endterm.project.dto.UserNameWithImageDto;

import com.endterm.project.entities.MyUser;
import com.endterm.project.repos.UserRepo;
import com.endterm.project.services.UserServiceInterface;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserRestController {

    @Autowired
    UserServiceInterface userServiceInterface;
    @Autowired
    UserRepo userRepo;

    @ResponseBody
    @PostMapping("/searchUser")
    public List<MyUser> searchUser(@RequestHeader String name) {
        return userServiceInterface.searchUsersByName(name);
    }

    @ResponseBody
    @GetMapping("/testGetMethod")
    public List<MyUser> testGetMethod() {
        return userServiceInterface.searchUsersByName("Alexandros");
    }

    @ResponseBody
    @GetMapping("/searchUsers")
    public ResponseEntity<List<SearchImageDto>> searchUsers(@RequestHeader String input) {
        List<UserNameWithImageDto> list = userServiceInterface.searchUserByFirstnameOrLastname(input);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = userRepo.findUserIdByEmail(user.getUsername()).orElseThrow(() -> new RuntimeException("Error: User Id not found"));

        List<SearchImageDto> search = list.stream()
                .filter(u -> u.getUserId() != currentUserId)
                .map(file -> new SearchImageDto(
                        file.getFirstName(),
                        file.getLastName(),
                        file.getUserId(),
                        file.getImage().getType(),
                        file.getImage().getSize()
                )).collect(Collectors.toList());


        return ResponseEntity.status(HttpStatus.OK).body(search);
    }

    @ResponseBody
    @GetMapping("/userDetails")
    public UserDto getUserDetails(Principal principal) {
        return userServiceInterface.getCurrentUser();
    }

   
    @ResponseBody
    @GetMapping("/testSearch")
    public UserNameWithImageDto testSearch() {
        UserNameWithImageDto test = userServiceInterface.searchUserByFirstnameOrLastname("Ale").get(0);
        return test;
    }
}
