package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.ITParamDefinitionDAO;
import com.lentice.idm.ws.model.ITParamDefinition;

@Repository
public class ITParamDefinitionDAOImpl implements ITParamDefinitionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ITParamDefinitionDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addITParamDefinition(ITParamDefinition i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(i);
		logger.info("ITResourceParams saved successfully, Organization Details="+i);
	}

	@Override
	public void updateITParamDefinition(ITParamDefinition i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(i);
		logger.info("ITResourceParams updated successfully, Organization Details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITParamDefinition> listITResourceParams() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ITParamDefinition> ITResourceParamsList = session.createQuery("from ITParamDefinition").list();
		for(ITParamDefinition i : ITResourceParamsList){
			logger.info("ITResourceParams List::"+i);
		}
		return ITResourceParamsList;
	}

	@Override
	public ITParamDefinition getITParamDefinitionById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ITParamDefinition i = (ITParamDefinition) session.load(ITParamDefinition.class, new Integer(id));
		logger.info("ITResourceParams loaded successfully, ITResourceParams details="+i);
		return i;
	}

	@Override
	public void removeITParamDefinition(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ITParamDefinition i = (ITParamDefinition) session.load(ITParamDefinition.class, new Integer(id));
		if(null != i){
			session.delete(i);
		}
		logger.info("ITResourceParams deleted successfully, ITResourceParams details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITParamDefinition> getITParamDefinitionByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ITParamDefinition.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

}