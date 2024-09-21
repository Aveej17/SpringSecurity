package org.jeeva.springsecurity.service;

import org.jeeva.springsecurity.model.User;
import org.jeeva.springsecurity.repository.UserRepository;
import org.jeeva.springsecurity.request.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }

    public User registerUser(UserRegisterRequest userRegisterRequest){


        userRegisterRequest.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));

        User newUser = userRegisterRequest.toUser();

        return userRepository.save(newUser);
    }

    public  String login(UserRegisterRequest userRegisterRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRegisterRequest.getUsername(),userRegisterRequest.getPassword()));

        if(authentication.isAuthenticated()){

            return jwtService.generateToken(userRegisterRequest.getUsername());
        }
        return "Failed";
    }
}
