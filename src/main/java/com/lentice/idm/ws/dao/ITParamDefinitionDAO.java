package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.ITParamDefinition;

public interface ITParamDefinitionDAO {

	public void addITParamDefinition(ITParamDefinition i);
	public void updateITParamDefinition(ITParamDefinition i);
	public List<ITParamDefinition> listITResourceParams();
	public List<ITParamDefinition> getITParamDefinitionByName(String name);
	public ITParamDefinition getITParamDefinitionById(int id);
	public void removeITParamDefinition(int id);
}