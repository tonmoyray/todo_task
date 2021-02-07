package com.example.demo.web;

import com.example.demo.model.User;
import com.example.demo.model.error.CustomSuccessResponse;
import com.example.demo.model.exception.UserAlreadyExistsException;
import com.example.demo.model.exception.UserCredentialDoesNotMatchException;
import com.example.demo.model.exception.UserDoesNotExistException;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


@RestController
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public CustomSuccessResponse registerUser(@Valid @RequestBody User newUser){
        System.out.println("New user: " + newUser.toString());

        User existingUser = userService.findByEmail(newUser.getEmail());
        if (existingUser != null) {
            System.out.println("existingUser: " + existingUser.toString());
            throw new UserAlreadyExistsException(newUser.getEmail());
        }else{

            String originalPassword = newUser.getPasswordHash();
            newUser.setPasswordHash(hashedPassword(originalPassword));

            User savedUser = userService.create(newUser);

            savedUser.setPasswordHash(originalPassword);

            CustomSuccessResponse success = new CustomSuccessResponse();
            success.setTimestamp(LocalDateTime.now());
            success.setMessage("Registration Successful!");
            success.setStatus(HttpStatus.OK.value());
            success.setUser(savedUser);

            return success;
        }
    }

    @PostMapping("/users/login")
    public CustomSuccessResponse login(@RequestBody User user){
        System.out.println("User: " + user);

        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new UserDoesNotExistException(user.getEmail());
        }else{

            if(existingUser.getPasswordHash().equals(hashedPassword(user.getPasswordHash()))){

                user.setUserName(existingUser.getUserName());

                CustomSuccessResponse success = new CustomSuccessResponse();
                success.setTimestamp(LocalDateTime.now());
                success.setMessage("Login Successful!");
                success.setStatus(HttpStatus.OK.value());
                success.setUser(user);
                return success;

            }else{
                throw new UserCredentialDoesNotMatchException(user.getEmail());
            }
        }
    }

    @PostMapping("/users/update")
    public CustomSuccessResponse updateUser(@Valid @RequestBody User updatedUser){
        System.out.println("updatedUser: " + updatedUser.toString());

        User existingUser = userService.findByEmail(updatedUser.getEmail());
        if (existingUser == null) {
            throw new UserDoesNotExistException(updatedUser.getEmail());
        }else{
            System.out.println("existingUser: " + existingUser.toString());

            existingUser.setUserName(updatedUser.getUserName());
            existingUser.setPasswordHash(hashedPassword(updatedUser.getPasswordHash()));

            User savedUser = userService.update(existingUser);

            savedUser.setPasswordHash(updatedUser.getPasswordHash());

            CustomSuccessResponse success = new CustomSuccessResponse();
            success.setTimestamp(LocalDateTime.now());
            success.setMessage("Update Successful!");
            success.setStatus(HttpStatus.OK.value());
            success.setUser(savedUser);

            return success;
        }
    }


    @PostMapping("/users/delete")
    public CustomSuccessResponse deleteUser(@RequestBody User deleteUser){
        System.out.println("deleteUser: " + deleteUser.toString());

        User existingUser = userService.findByEmail(deleteUser.getEmail());
        if (existingUser == null) {
            throw new UserDoesNotExistException(deleteUser.getEmail());
        }else{
            System.out.println("existingUser: " + existingUser.toString());

            userService.delete(existingUser);

            CustomSuccessResponse success = new CustomSuccessResponse();
            success.setTimestamp(LocalDateTime.now());
            success.setMessage("Deletion Successful!");
            success.setStatus(HttpStatus.OK.value());

            return success;
        }
    }




    private String hashedPassword(String password){


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }


}
