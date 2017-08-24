package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.Organization;

public interface OrganizationDAO {

	public void addOrganization(Organization o);
	public void updateOrganization(Organization o);
	public List<Organization> listOrganizations();
	public List<Organization> getOrganizationByName(String name);
	public Organization getOrganizationById(int id);
	public void removeOrganization(int id);
}