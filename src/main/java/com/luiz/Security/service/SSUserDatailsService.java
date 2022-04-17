package com.luiz.Security.service;

import java.util.HashSet;
//import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luiz.Security.model.Role;
//import com.luiz.Security.model.Role;
import com.luiz.Security.model.User;
import com.luiz.Security.repository.UserRepository;

@Transactional
@Service
public class SSUserDatailsService implements UserDetailsService{

	private UserRepository userRepository;
	
	public SSUserDatailsService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User user=userRepository.findByUsername(username);
			if(user==null) {
				return null;
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthories(user));
		}
		catch(Exception e) {
			throw new UsernameNotFoundException("User Não encontrado");
		}
	}
	//Retirar toda autoridade dos usuários
	private Set<GrantedAuthority> getAuthories(User user){
		Set<GrantedAuthority>authorities=new HashSet<>();
		for(Role role: user.getRoles()){
			GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(role.getRole());
			authorities.add(grantedAuthority);
//			
		}
		return authorities;
	}
}
