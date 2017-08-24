package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.User;

public interface UserDAO {

	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
}