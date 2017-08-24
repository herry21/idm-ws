package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.RoleDAO;
import com.lentice.idm.ws.model.Role;
//import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addRole(Role r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(r);
		logger.info("Role saved successfully, Role Details="+r);
	}

	@Override
	public void updateRole(Role r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(r);
		logger.info("Role updated successfully, Role Details="+r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> listRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Role> roleList = session.createQuery("from Role").list();
		for(Role r : roleList){
			logger.info("Role List::"+r);
		}
		return roleList;
	}

	@Override
	public Role getRoleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Role r = (Role) session.load(Role.class, new Integer(id));
		logger.info("Role loaded successfully, Role details="+r);
		return r;
	}

	@Override
	public void removeRole(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Role r = (Role) session.load(Role.class, new Integer(id));
		if(null != r){
			try {
				session.delete(r);
			}
			catch(ConstraintViolationException cve) {
				logger.error("##############################Error constraint######################3");
				cve.printStackTrace();
			}
			catch(Exception e) {
				logger.error("##############################Error######################3");
				e.printStackTrace();
			}
		}
		logger.info("Role deleted successfully, Role details="+r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoleByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}
}