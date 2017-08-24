package com.lentice.idm.ws.service;

import java.util.List;

import com.lentice.idm.ws.model.User;

public interface UserService {

	public void addUser(User u);
	public void updateUser(User u);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
	
}