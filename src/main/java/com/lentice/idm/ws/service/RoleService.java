package com.lentice.idm.ws.service;

import java.util.List;

import com.lentice.idm.ws.model.Role;

public interface RoleService {

	public void addRole(Role u);
	public void updateRole(Role u);
	public List<Role> listRoles();
	public List<Role> getRoleByName(String name);
	public Role getRoleById(int id);
	public void removeRole(int id);
	
}