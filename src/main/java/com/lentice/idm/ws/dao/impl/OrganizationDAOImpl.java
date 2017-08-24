package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.OrganizationDAO;
import com.lentice.idm.ws.model.Organization;

@Repository
public class OrganizationDAOImpl implements OrganizationDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addOrganization(Organization o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(o);
		logger.info("Organization saved successfully, Organization Details="+o);
	}

	@Override
	public void updateOrganization(Organization o) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(o);
		logger.info("Organization updated successfully, Organization Details="+o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> listOrganizations() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Organization> orgsList = session.createQuery("from Organization").list();
		for(Organization o : orgsList){
			logger.info("Organization List::"+o);
		}
		return orgsList;
	}

	@Override
	public Organization getOrganizationById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Organization o = (Organization) session.load(Organization.class, new Integer(id));
		logger.info("Organization loaded successfully, Person details="+o);
		return o;
	}

	@Override
	public void removeOrganization(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Organization o = (Organization) session.load(Organization.class, new Integer(id));
		if(null != o){
			session.delete(o);
		}
		logger.info("Organization deleted successfully, organization details="+o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Organization.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

}