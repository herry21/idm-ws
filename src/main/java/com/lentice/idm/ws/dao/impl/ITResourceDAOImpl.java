package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.ITResourceDAO;
import com.lentice.idm.ws.model.ITResource;

@Repository
public class ITResourceDAOImpl implements ITResourceDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ITResourceDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addITResource(ITResource i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(i);
		logger.info("ITResource saved successfully, Organization Details="+i);
	}

	@Override
	public void updateITResource(ITResource i) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(i);
		logger.info("ITResource updated successfully, Organization Details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITResource> listITResources() {
		Session session = this.sessionFactory.getCurrentSession();
		List<ITResource> ITResourceList = session.createQuery("from ITResource").list();
		for(ITResource i : ITResourceList){
			logger.info("ITResource List::"+i);
		}
		return ITResourceList;
	}

	@Override
	public ITResource getITResourceById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		ITResource i = (ITResource) session.load(ITResource.class, new Integer(id));
		logger.info("ITResource loaded successfully, ITResource details="+i);
		return i;
	}

	@Override
	public void removeITResource(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		ITResource i = (ITResource) session.load(ITResource.class, new Integer(id));
		if(null != i){
			session.delete(i);
		}
		logger.info("ITResource deleted successfully, ITResource details="+i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ITResource> getITResourceByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ITResource.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

}