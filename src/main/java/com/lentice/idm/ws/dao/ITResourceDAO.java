package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.ITResource;

public interface ITResourceDAO {

	public void addITResource(ITResource i);
	public void updateITResource(ITResource i);
	public List<ITResource> listITResources();
	public List<ITResource> getITResourceByName(String name);
	public ITResource getITResourceById(int id);
	public void removeITResource(int id);
}