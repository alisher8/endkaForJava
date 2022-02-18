package com.endterm.project.services;

import com.endterm.project.dto.UserDto;
import com.endterm.project.dto.UserNameWithImageDto;
import com.endterm.project.entities.Image;
import com.endterm.project.entities.ImageBackground;
import com.endterm.project.entities.MyUser;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    
     List<MyUser> searchUsersByName(String name);

     UserDto getCurrentUser();

     UserDto getCurrentUser(int id);

     List<UserDto> getAllUsers();

     MyUser findById(Integer userid);

      Integer findUserIdByEmail(String email);

    String findCurrentUsername();

    void userSave(MyUser user);

    Image findImageProfileFromUserId(Integer userid);

     List<UserNameWithImageDto> searchUserByFirstnameOrLastname(String input);

     MyUser getUserDetails(String email);

     ImageBackground findImageBackgroundFromUserId(Integer userid);
}
