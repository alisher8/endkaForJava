package com.endterm.project.controllers;

import com.endterm.project.entities.Image;
import com.endterm.project.entities.MyUser;
import com.endterm.project.entities.Role;
import com.endterm.project.repos.ImageRepo;
import com.endterm.project.repos.RoleRepo;
import com.endterm.project.repos.UserRepo;
import com.endterm.project.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserValidator userValidator;
    @Autowired
    ImageRepo imageRepo;

    @InitBinder
    private void InitBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }


    @GetMapping("/register")
    public String registerPage(ModelMap mm) {
        mm.addAttribute("newUser", new MyUser());
        System.out.println(MyUser.class);
        return "register";
    }

    @PostMapping("/doregister")
    public String registrationSubmit(ModelMap mm,
                                     @Valid @ModelAttribute("newUser") MyUser myUser,
                                     BindingResult bindingResult) {

        System.out.println(bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        String encodedPass = passwordEncoder.encode(myUser.getPassword());
        myUser.setPassword(encodedPass);

        List<Role> roles = new ArrayList<>();

        Role userRole = roleRepo.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role was not found"));
        roles.add(userRole);

        myUser.setRoles(roles);

        Image image = imageRepo.findById(149).orElseThrow(()->new IllegalArgumentException("image not found"));
        Image newImage= new Image();
        newImage.setFile(image.getFile());
        newImage.setTitle(image.getTitle());
        newImage.setSize(image.getSize());
        newImage.setType(image.getType());
        myUser.setImage(imageRepo.save(newImage));
        userRepo.save(myUser);
        return "redirect:successPage";
    }

    @GetMapping("/successPage")
    public String successPage() {
        return "success";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


}
