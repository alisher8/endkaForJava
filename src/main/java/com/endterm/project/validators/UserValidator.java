package com.endterm.project.validators;


import com.endterm.project.entities.MyUser;
import com.endterm.project.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;


@Component
public class UserValidator implements Validator {

    @Autowired
    UserRepo userRepo;


    @Override
    public boolean supports(Class<?> clazz) {
        return MyUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MyUser userForm = (MyUser) target;

        MyUser userWithSameEmailFound = userRepo.findByEmailNotOptional(userForm.getEmail());
        if (userWithSameEmailFound != null) {
            errors.rejectValue("email", "email.exists");
        }


        if (userForm.getPassword().length() > 30) {
            errors.rejectValue("password", "password.tooLong");
        } else {
            Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$");
            boolean matchFound = pattern.matcher(userForm.getPassword()).find();
            if (!matchFound) {
                errors.rejectValue("password", "password.unsafe");
            }
        }

        String userFirstName = userForm.getFirstName();
        String userLastName = userForm.getLastName();

        if (userFirstName.length() > 2 && userFirstName.length() <= 15) {
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            boolean matchFound = pattern.matcher(userFirstName).find();
            if (!matchFound) {
                errors.rejectValue("firstName", "firstName.onlyLetters");
            }
        }

        if (userLastName.length() > 2 && userLastName.length() <= 20) {
            Pattern pattern = Pattern.compile("^[A-Za-z]+$");
            boolean matchFound = pattern.matcher(userLastName).find();
            if (!matchFound) {
                errors.rejectValue("lastName", "lastName.onlyLetters");
            }
        }


        if (userForm.getBirthday() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date pastDate = simpleDateFormat.parse("1920-01-01");
                if (userForm.getBirthday().compareTo(pastDate) < 0) {
                    errors.rejectValue("birthday", "birthday.past");
                }
            } catch (ParseException | NullPointerException e) {
                e.printStackTrace();

            }
            java.util.Date currentDate = new java.util.Date();
            if (userForm.getBirthday().compareTo(currentDate) > 0) {
                errors.rejectValue("birthday", "birthday.future");
            }
        }
    }
}
