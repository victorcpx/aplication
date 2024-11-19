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

    @Override
    public User getUserById(Long id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception ("The user does not exists."));
    }

    @Override
    public User updateUser(User getUser) throws Exception {
        User setUser = getUserById(getUser.getId());
        mapUser(getUser, setUser);
        return repository.save(setUser);
    }
    /* Map Everything but hte password */
    protected void mapUser(User from, User to) {  
        to.setUsername(from.getUsername());  
        to.setFirstName(from.getFirstName());  
        to.setLastName(from.getLastName());  
        to.setEmail(from.getEmail());  
        to.setRoles(from.getRoles());  
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        User user = getUserById(id);
        repository.delete(user);
    }

}