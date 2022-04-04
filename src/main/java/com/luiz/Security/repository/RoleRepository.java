package com.luiz.Security.repository;

import org.springframework.data.repository.CrudRepository;

import com.luiz.Security.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
   Role findByRole(String role);
}
