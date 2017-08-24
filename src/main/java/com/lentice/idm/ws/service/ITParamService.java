package com.lentice.idm.ws.service;

import java.util.List;

import com.lentice.idm.ws.model.ITParam;

public interface ITParamService {

	public void addITParam(ITParam i);
	public void updateITParam(ITParam i);
	public List<ITParam> listITParams();
	public ITParam getITParamById(int id);
	public void removeITParam(int id);
}