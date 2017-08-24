package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.ITParamDAO;
import com.lentice.idm.ws.model.ITParam;
import com.lentice.idm.ws.service.ITParamService;

@Service
public class ITParamServiceImpl implements ITParamService {
	
	private ITParamDAO itParamDAO;

	public void setItParamDAO(ITParamDAO itParamDAO) {
		this.itParamDAO = itParamDAO;
	}


	@Override
	@Transactional
	public void addITParam(ITParam i) {
		this.itParamDAO.addITParam(i);
	}

	@Override
	@Transactional
	public void updateITParam(ITParam i) {
		this.itParamDAO.updateITParam(i);
	}

	@Override
	@Transactional
	public List<ITParam> listITParams() {
		return this.itParamDAO.listITParams();
	}

	@Override
	@Transactional
	public ITParam getITParamById(int id) {
		return this.itParamDAO.getITParamById(id);
	}


	@Override
	@Transactional
	public void removeITParam(int id) {
		this.itParamDAO.removeITParam(id);
	}	

}