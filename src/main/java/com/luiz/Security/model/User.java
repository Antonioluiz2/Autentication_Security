package com.luiz.Security.model;

import java.util.Collection;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="email", nullable = false )
	private String email;
	@Column(name= "password")
	private String password;
	@Column(name= "name")
	private String name;
	@Column(name= "last_name")
	private String last_name;
	@Column(name= "enabled")
	private boolean enabled;
	@Column(name= "username")
	private String username;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name= "User_id"),inverseJoinColumns = @JoinColumn(name="role_id"))
	private Collection<Role>roles;

	public User() {
		
	}
	
	public User(long id, String email, String password, String name, String last_name, boolean enabled, String username,
			Collection<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.last_name = last_name;
		this.enabled = enabled;
		this.username = username;
		this.roles = roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	
}
