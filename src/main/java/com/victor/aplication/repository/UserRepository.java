package com.victor.aplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.victor.aplication.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
