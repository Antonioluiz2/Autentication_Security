package com.luiz.Security.repository;

import org.springframework.data.repository.CrudRepository;

import com.luiz.Security.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUsername(String username);

}
