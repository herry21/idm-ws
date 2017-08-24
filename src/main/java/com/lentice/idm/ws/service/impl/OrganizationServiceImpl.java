package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.OrganizationDAO;
import com.lentice.idm.ws.model.Organization;
import com.lentice.idm.ws.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	private OrganizationDAO orgDAO;

	public void setOrgDAO(OrganizationDAO orgDAO) {
		this.orgDAO = orgDAO;
	}

	@Override
	@Transactional
	public void addOrganization(Organization o) {
		this.orgDAO.addOrganization(o);
	}

	@Override
	@Transactional
	public void updateOrganization(Organization o) {
		this.orgDAO.updateOrganization(o);
	}

	@Override
	@Transactional
	public List<Organization> listOrganizations() {
		return this.orgDAO.listOrganizations();
	}

	@Override
	@Transactional
	public Organization getOrganizationById(int id) {
		return this.orgDAO.getOrganizationById(id);
	}

	@Override
	@Transactional
	public void removeOrganization(int id) {
		this.orgDAO.removeOrganization(id);
	}

	@Override
	@Transactional
	public List<Organization> getOrganizationByName(String name) {
		return this.orgDAO.getOrganizationByName(name);
	}

}