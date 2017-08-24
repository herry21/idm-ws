package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.ITParam;

public interface ITParamDAO {

	public void addITParam(ITParam i);
	public void updateITParam(ITParam i);
	public List<ITParam> listITParams();
	public ITParam getITParamById(int id);
	public void removeITParam(int id);
}