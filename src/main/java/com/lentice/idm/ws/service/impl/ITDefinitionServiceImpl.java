package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.ITDefinitionDAO;
import com.lentice.idm.ws.model.ITDefinition;
import com.lentice.idm.ws.service.ITDefinitionService;

@Service
public class ITDefinitionServiceImpl implements ITDefinitionService {
	
	private ITDefinitionDAO itDefDAO;

	
	public void setItDefDAO(ITDefinitionDAO itDefDAO) {
		this.itDefDAO = itDefDAO;
	}

	@Override
	@Transactional
	public void addITResourceType(ITDefinition i) {
		this.itDefDAO.addITResourceType(i);
	}

	@Override
	@Transactional
	public void updateITResourceType(ITDefinition i) {
		this.itDefDAO.updateITResourceType(i);
		
	}

	@Override
	@Transactional
	public List<ITDefinition> listITResourceType() {
		return this.itDefDAO.listITResourceType();
	}

	@Override
	@Transactional
	public ITDefinition getITResourceTypeById(int id) {
		return this.itDefDAO.getITResourceTypeById(id);
	}

	@Override
	@Transactional
	public void removeITResourceType(int id) {
		this.itDefDAO.removeITResourceType(id);
		
	}

	@Override
	@Transactional
	public List<ITDefinition> getITDefinitionsByName(String name) {
		return this.itDefDAO.getITDefinitionsByName(name);
	}

}