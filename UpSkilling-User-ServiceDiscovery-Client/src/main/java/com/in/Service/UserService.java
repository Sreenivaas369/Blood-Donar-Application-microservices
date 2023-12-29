package com.in.Service;

import org.springframework.http.ResponseEntity;

import com.in.Entity.User;
import com.in.ExceptionHandler.UserNotFoundException;

import jakarta.validation.Valid;

public interface UserService {


    String updatePassword(String userid, @Valid User user);

    String userLogin(@Valid String userid, String password) throws InterruptedException;

    String userLoginDetails(@Valid User user) throws UserNotFoundException;


    User AddUser(String userid, String password);


    User saveuser(User user);
}
