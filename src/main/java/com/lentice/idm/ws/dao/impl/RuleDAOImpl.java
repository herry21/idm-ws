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

import com.lentice.idm.ws.dao.RuleDAO;
import com.lentice.idm.ws.model.Rule;
import com.lentice.idm.ws.model.RuleElement;

@Repository
public class RuleDAOImpl implements RuleDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addRule(Rule r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(r);
		logger.info("Rule saved successfully, Rule Details="+r);
	}

	@Override
	public void updateRule(Rule r) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(r);
		logger.info("Rule updated successfully, Rule Details="+r);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rule> listRules() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Rule> ruleList = session.createQuery("from Rule").list();
		for(Rule r : ruleList){
			logger.info("Rule List::"+r);
		}
		return ruleList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rule> getRuleByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Rule.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

	@Override
	public Rule getRuleById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Rule r = (Rule) session.load(Rule.class, new Integer(id));
		logger.info("Rule loaded successfully, Rule details="+r);
		return r;
	}

	@Override
	public void removeRule(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Rule i = (Rule) session.load(Rule.class, new Integer(id));
		if(null != i){
			session.delete(i);
		}
		logger.info("Rule deleted successfully, Rule details="+i);
	}

	@Override
	public boolean addRuleElement(int id, RuleElement re) {
		Session session = this.sessionFactory.getCurrentSession();
		Rule i = (Rule) session.load(Rule.class, new Integer(id));
		if(null != i){
			Set<RuleElement> elements = i.getRuleElements();
			elements.add(re);
			i.setRuleElements(elements);
			session.update(i);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeRuleElement(int id, RuleElement re) {
		Session session = this.sessionFactory.getCurrentSession();
		Rule i = (Rule) session.load(Rule.class, new Integer(id));
		if(null != i){
			
			System.out.println("will remove:");
			System.out.println("Key="+re.getKey()+", Name="+re.getName());
			
			Set<RuleElement> elements = i.getRuleElements();
			System.out.println("before");
			for(RuleElement rue : elements) {
				System.out.println("Key="+rue.getKey()+", Name="+rue.getName());
			}
			boolean is = elements.remove(re);
			if(!is) {
				System.out.println("GAGAGAGAL");
			}
			System.out.println("after");
			for(RuleElement rue : elements) {
				System.out.println("Key="+rue.getKey()+", Name="+rue.getName());
			}
			i.setRuleElements(elements);
			session.update(i);
			return true;
		}
		return false;
	}

}