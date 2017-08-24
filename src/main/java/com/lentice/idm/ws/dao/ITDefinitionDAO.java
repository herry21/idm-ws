package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.ITDefinition;

public interface ITDefinitionDAO {

	public void addITResourceType(ITDefinition i);
	public void updateITResourceType(ITDefinition i);
	public List<ITDefinition> listITResourceType();
	public ITDefinition getITResourceTypeById(int id);
	public void removeITResourceType(int id);
	public List<ITDefinition> getITDefinitionsByName(String name);
}