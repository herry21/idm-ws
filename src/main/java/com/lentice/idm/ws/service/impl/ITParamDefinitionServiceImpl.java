package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.ITParamDefinitionDAO;
import com.lentice.idm.ws.model.ITParamDefinition;
import com.lentice.idm.ws.service.ITParamDefinitionService;

@Service
public class ITParamDefinitionServiceImpl implements ITParamDefinitionService {
	
	private ITParamDefinitionDAO itrtParamDAO;

	
	public void setItrtParamDAO(ITParamDefinitionDAO itrtParamDAO) {
		this.itrtParamDAO = itrtParamDAO;
	}

	@Override
	@Transactional
	public void addITParamDefinition(ITParamDefinition i) {
		this.itrtParamDAO.addITParamDefinition(i);
	}

	@Override
	@Transactional
	public void updateITParamDefinition(ITParamDefinition i) {
		this.itrtParamDAO.updateITParamDefinition(i);
		
	}

	@Override
	@Transactional
	public List<ITParamDefinition> listITParamDefinitions() {
		return this.itrtParamDAO.listITResourceParams();
	}

	@Override
	@Transactional
	public ITParamDefinition getITParamDefinitionById(int id) {
		return this.itrtParamDAO.getITParamDefinitionById(id);
	}

	@Override
	@Transactional
	public void removeITParamDefinition(int id) {
		this.itrtParamDAO.removeITParamDefinition(id);
		
	}

	@Override
	public List<ITParamDefinition> getITParamDefinitionByName(String name) {
		return this.itrtParamDAO.getITParamDefinitionByName(name);
	}
}