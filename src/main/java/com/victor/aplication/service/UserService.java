package com.victor.aplication.service;

import com.victor.aplication.dto.ChangePasswordForm;
import com.victor.aplication.entity.User;

public interface UserService {

    public Iterable<User> getAllUsers();

    public User createUser(User user) throws Exception;

    public User getUserById(Long id) throws Exception;

    public User updateUser(User user) throws  Exception;

    public void deleteUser(Long id) throws Exception;

    public User changePassword(ChangePasswordForm form) throws Exception;

}
