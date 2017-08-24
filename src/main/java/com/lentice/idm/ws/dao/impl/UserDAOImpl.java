package com.lentice.idm.ws.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.UserDAO;
import com.lentice.idm.ws.model.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("Person saved successfully, Person Details="+u);
	}

	@Override
	public void updateUser(User u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("Person updated successfully, Person Details="+u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		for(User u : usersList){
			logger.info("User List::"+u);
		}
		return usersList;
	}

	@Override
	public User getUserById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		User u = (User) session.load(User.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+u);
		return u;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, new Integer(id));
		if(null != u){
			session.delete(u);
		}
		logger.info("Person deleted successfully, person details="+u);
	}

}