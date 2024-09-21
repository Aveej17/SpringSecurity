package org.jeeva.springsecurity.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.jeeva.springsecurity.model.User;
import org.jeeva.springsecurity.request.UserRegisterRequest;
import org.jeeva.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;



    List<User> users = new ArrayList<>(List.of(
            new User(1,"Jeeva","java"),
            new User(2,"Kiran","python"),
            new User(3,"Mansy","BlockChain")
    ));

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");

    }
    @GetMapping("/")
    public List<User> getStudents(){
        return  users;
    }


    @PostMapping("/adduser")
    public void addStudent(@RequestBody User user){
        users.add(user);
    }

    @PostMapping("/register")
    public User register(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.registerUser(userRegisterRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRegisterRequest userRegisterRequest){

        return userService.login(userRegisterRequest);
    }

}


