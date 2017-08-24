package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.ITResourceDAO;
import com.lentice.idm.ws.model.ITResource;
import com.lentice.idm.ws.service.ITResourceService;

@Service
public class ITResourceServiceImpl implements ITResourceService {
	
	private ITResourceDAO itrDAO;

	
	public void setItrDAO(ITResourceDAO itrDAO) {
		this.itrDAO = itrDAO;
	}

	@Override
	@Transactional
	public void addITResource(ITResource i) {
		this.itrDAO.addITResource(i);
	}

	@Override
	@Transactional
	public void updateITResource(ITResource i) {
		this.itrDAO.updateITResource(i);
		
	}

	@Override
	@Transactional
	public List<ITResource> listITResources() {
		return this.itrDAO.listITResources();
	}

	@Override
	@Transactional
	public ITResource getITResourceById(int id) {
		return this.itrDAO.getITResourceById(id);
	}

	@Override
	@Transactional
	public void removeITResource(int id) {
		this.itrDAO.removeITResource(id);
		
	}

	@Override
	@Transactional
	public List<ITResource> getITResourceByName(String name) {
		return this.itrDAO.getITResourceByName(name);
	}

}