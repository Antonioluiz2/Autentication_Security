package com.luiz.Security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.luiz.Security.model.Role;
import com.luiz.Security.model.User;
//import com.luiz.Security.model.User;
import com.luiz.Security.repository.RoleRepository;
import com.luiz.Security.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role("USER"));
		roleRepository.save(new Role("ADMIN"));
		
		Role adminRole = roleRepository.findByRole("ADMIN");
		Role userRole = roleRepository.findByRole("USER");
		
		User user=new User("admin@code.com", passwordEncoder.encode("password"),"Admin", "Super", true,"admin", null);
		user.setRoles(Arrays.asList(adminRole));
		userRepository.save(user);
		
		user=new User("user@code.com", passwordEncoder.encode("password"),"User", "Super", true,"user", null);
		user.setRoles(Arrays.asList(adminRole));
		userRepository.save(user);
	}

}
