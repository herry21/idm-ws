package com.lentice.idm.ws.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.lentice.idm.ws.dao.RuleElementDAO;
import com.lentice.idm.ws.model.RuleElement;

@Repository
public class RuleElementDAOImpl implements RuleElementDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleElementDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addRuleElement(RuleElement re) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(re);
		logger.info("Rule saved successfully, RuleElement Details="+re);
	}

	@Override
	public void updateRuleElement(RuleElement re) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(re);
		logger.info("Rule updated successfully, Rule Details="+re);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RuleElement> listRuleElements() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RuleElement> ruleElementList = session.createQuery("from RuleElement").list();
		for(RuleElement re : ruleElementList){
			logger.info("Rule List::"+re);
		}
		return ruleElementList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RuleElement> getRuleElementByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(RuleElement.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

	@Override
	public RuleElement getRuleElementById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		RuleElement re = (RuleElement) session.load(RuleElement.class, new Integer(id));
		logger.info("Rule loaded successfully, RuleElement details="+re);
		return re;
	}

	@Override
	public void removeRuleElement(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		RuleElement re = (RuleElement) session.load(RuleElement.class, new Integer(id));
		if(null != re){
			session.delete(re);
		}
		logger.info("Rule deleted successfully, RuleElement details="+re);
	}
}