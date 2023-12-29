package com.in.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.in.Entity.User;
import com.in.ExceptionHandler.UserNotFoundException;
import com.in.Service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService uService;

    @PostMapping("/saveuser")
    public ResponseEntity<User> Saveuser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(uService.saveuser(user),HttpStatus.CREATED);
    }


    // For Login Purpose by using Post method

    @PostMapping("/userLoginDetails")
    public ResponseEntity<String> userLoginDetails(@Valid @RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(uService.userLoginDetails(user),HttpStatus.ACCEPTED);
    }


    // To Update the password

    @PutMapping("/UpdatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam(required = true) String userid,
                                                 @Valid @RequestBody User user){
        return new ResponseEntity<>(uService.updatePassword(userid, user), HttpStatus.ACCEPTED);
    }

    // For Login Purpose by using Get method

    @GetMapping("/userLogin")
    @HystrixCommand(fallbackMethod = "fallbackStringuser", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1")
    })
    public ResponseEntity<String> StringuserLogin(@Valid @RequestParam(required = true) String userid,
                                                  @RequestParam(required = true) String password) throws InterruptedException {
        return new ResponseEntity<>(uService.userLogin(userid, password),HttpStatus.ACCEPTED);
    }

    public String fallbackStringuser(){
        return "Fallback method is implemented";

    }

}
