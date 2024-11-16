package com.victor.aplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.victor.aplication.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
