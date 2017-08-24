package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.ITParamDAO;
import com.lentice.idm.ws.model.ITParam;

@Repository
public class ITParamDAOImpl implements ITParamDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ITParamDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addITParam(ITParam i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(i);
		logger.info("ITResourceParams saved successfully, ITParam Details="+i);
	}

	@Override
	public void updateITParam(ITParam i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(i);
		logger.info("ITParams updated successfully, ITParam Details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITParam> listITParams() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ITParam> ITParamsList = session.createQuery("from ITParam").list();
		for(ITParam i : ITParamsList){
			logger.info("ITParams List::"+i);
		}
		return ITParamsList;
	}

	@Override
	public ITParam getITParamById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ITParam i = (ITParam) session.load(ITParam.class, new Integer(id));
		logger.info("ITParams loaded successfully, ITParams details="+i);
		return i;
	}

	@Override
	public void removeITParam(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ITParam i = (ITParam) session.load(ITParam.class, new Integer(id));
		if(null != i){
			session.delete(i);
		}
		logger.info("ITParams deleted successfully, ITParams details="+i);
	}
}