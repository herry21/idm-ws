package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.ITDefinitionDAO;
import com.lentice.idm.ws.model.ITDefinition;

@Repository
public class ITDefinitionDAOImpl implements ITDefinitionDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ITDefinitionDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addITResourceType(ITDefinition i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(i);
		logger.info("ITResourceType saved successfully, Organization Details="+i);
	}

	@Override
	public void updateITResourceType(ITDefinition i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(i);
		logger.info("ITResourceType updated successfully, Organization Details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITDefinition> listITResourceType() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ITDefinition> itResourceTypeList = session.createQuery("from ITDefinition").list();
		for(ITDefinition i : itResourceTypeList){
			logger.info("ITResourceType List::"+i);
		}
		return itResourceTypeList;
	}

	@Override
	public ITDefinition getITResourceTypeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ITDefinition i = (ITDefinition) session.load(ITDefinition.class, new Integer(id));
		logger.info("ITResourceType loaded successfully, ITResourceType details="+i);
		return i;
	}

	@Override
	public void removeITResourceType(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ITDefinition i = (ITDefinition) session.load(ITDefinition.class, new Integer(id));
		if(null != i){
			session.delete(i);
		}
		logger.info("ITResourceType deleted successfully, ITResourceType details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITDefinition> getITDefinitionsByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		List<ITDefinition> results = null;
		
		try {
			Criteria crit = session.createCriteria(ITDefinition.class); //the persistent class goes here
			crit.add(Restrictions.like("name", "%" + name + "%") );
			//execute the query:
			results = (List<ITDefinition>) crit.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}