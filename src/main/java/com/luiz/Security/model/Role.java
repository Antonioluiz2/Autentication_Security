package com.luiz.Security.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 
	 @Column(unique = true)
	 private String role;
	 
	 @ManyToMany(mappedBy="roles", fetch=FetchType.LAZY)
	 private Collection<User>users;
	 
	 public Role(long id, String role, Collection<User> users) {
		super();
		this.id = id;
		this.role = role;
		this.users = users;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Collection<User> getUsers() {
		return users;
	}
	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	public Role() {
		 
	 }
public Role(String role) {
		 this.role=role;
	 }

}
