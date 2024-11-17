package com.victor.aplication.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victor.aplication.entity.User;
import com.victor.aplication.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    private boolean checkUsernameAvailable (User user) throws Exception{
         Optional<User> userFound = repository.findByUsername(user.getUsername());
         if (userFound.isPresent()){
            throw new Exception ("Username not available"); 
         }
         return true;
    }

    private boolean checkPasswordValid (User user) throws Exception{
        if (!user.getPassword().equals(user.getConfirmPassword())){
            throw new Exception("The passwords does not match");
        }
        return true;
    }

    @Override
    public User createUser(User user) throws  Exception{
        if (checkPasswordValid(user) && checkUsernameAvailable(user)){
            repository.save(user);
        }
        return user;
    }

}
