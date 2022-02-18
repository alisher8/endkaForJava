package com.endterm.project.repos;

import com.endterm.project.dto.UserNameWithImageDto;
import com.endterm.project.entities.Image;
import com.endterm.project.entities.ImageBackground;
import com.endterm.project.entities.MyUser;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {

   Optional<MyUser> findByEmail(String email);

    List<MyUser> findByFirstName(String name);

    @Query("SELECT u FROM MyUser u WHERE u.firstName LIKE :input% OR u.lastName LIKE :input%")
    List<MyUser> getUsersByFirstnameAndLastname(@Param("input") String input);

    @Query("SELECT u.userId FROM MyUser u WHERE u.email= :email")
    Optional<Integer> findUserIdByEmail(@Param("email") String email);

   @Query("SELECT i FROM MyUser u JOIN u.image i  ON u.userId= :userid")
   Optional<Image> findImageProfileFromUserId(@Param("userid") Integer userid);

    @Query("SELECT i FROM MyUser u JOIN u.imageBackground i  ON u.userId= :userid")
    Optional<ImageBackground> findImageBackgroundFromUserId(@Param("userid") Integer userid);

   @Query("SELECT u FROM MyUser u WHERE u.email = :input")
   MyUser findByEmailNotOptional(@Param("input") String email);

    @Query("SELECT new com.endterm.project.dto.UserNameWithImageDto (u.userId,u.firstName, u.lastName, i) FROM MyUser u JOIN u.image i ON (u.firstName LIKE :input% OR u.lastName LIKE :input%)")
    List<UserNameWithImageDto> findUsersForSearch(@Param("input") String input);

}
