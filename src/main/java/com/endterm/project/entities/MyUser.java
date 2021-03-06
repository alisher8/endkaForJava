package com.endterm.project.entities;

import com.endterm.project.websocketEntities.ChatNotification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class MyUser implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Email(message = "Email address is invalid")
    @Size(min = 4, max = 30, message = "Email must be between 4 and 30 characters")
    private String email;
    @Size(max = 60, message = "Password must be between 6 and 60 characters")
    private String password;
    @Size(min =3, max = 15, message = "First name must be between 3 and 15 characters")
    private String firstName;
    @Size(min =3, max = 20, message = "Last name must be between 3 and 20 characters")
    private String lastName;
    @NotNull(message = "Please provide a Date")
    @DateTimeFormat( pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private java.util.Date birthday;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "user_image",
            joinColumns =
            @JoinColumn(name = "user_id") ,
            inverseJoinColumns =
            @JoinColumn(name = "imageid") )
    @JsonIgnore
    private Image image;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinTable(name = "users_image_background",
            joinColumns =
            @JoinColumn(name = "user_id") ,
            inverseJoinColumns =
            @JoinColumn(name = "image_background_id") )
    @JsonIgnore
    private ImageBackground imageBackground;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "senderNotification")
    @JsonIgnore
    private List<ChatNotification> chatNotifications;


    public MyUser() {
    }

    public List<ChatNotification> getChatNotifications() {
        return chatNotifications;
    }

    public void setChatNotifications(List<ChatNotification> chatNotifications) {
        this.chatNotifications = chatNotifications;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageBackground getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(ImageBackground imageBackground) {
        this.imageBackground = imageBackground;
    }
}
