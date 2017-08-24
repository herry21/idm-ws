package com.lentice.idm.ws.service;

import java.util.List;

import com.lentice.idm.ws.model.ITResource;

public interface ITResourceService {

	public void addITResource(ITResource i);
	public void updateITResource(ITResource i);
	public List<ITResource> listITResources();
	public List<ITResource> getITResourceByName(String name);
	public ITResource getITResourceById(int id);
	public void removeITResource(int id);
	
}