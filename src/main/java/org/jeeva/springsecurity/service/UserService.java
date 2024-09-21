package org.jeeva.springsecurity.service;


import org.jeeva.springsecurity.model.User;
import org.jeeva.springsecurity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    private UserRepository userRepository;
    
    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        return users;
    }
}
