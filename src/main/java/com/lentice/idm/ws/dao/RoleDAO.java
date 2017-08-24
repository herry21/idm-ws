package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.Role;

public interface RoleDAO {

	public void addRole(Role p);
	public void updateRole(Role p);
	public List<Role> listRoles();
	public List<Role> getRoleByName(String name);
	public Role getRoleById(int id);
	public void removeRole(int id);
}