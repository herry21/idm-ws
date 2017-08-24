package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.RuleDAO;
import com.lentice.idm.ws.model.Rule;
import com.lentice.idm.ws.model.RuleElement;
import com.lentice.idm.ws.service.RuleService;

@Service
public class RuleServiceImpl implements RuleService {
	
	private RuleDAO ruleDAO;

	public void setRuleDAO(RuleDAO ruleDAO) {
		this.ruleDAO = ruleDAO;
	}

	@Override
	@Transactional
	public void addRule(Rule r) {
		this.ruleDAO.addRule(r);
	}

	@Override
	@Transactional
	public void updateRule(Rule r) {
		this.ruleDAO.updateRule(r);
	}

	@Override
	@Transactional
	public List<Rule> listRules() {
		return this.ruleDAO.listRules();
	}

	@Override
	@Transactional
	public Rule getRuleById(int id) {
		return this.ruleDAO.getRuleById(id);
	}

	@Override
	@Transactional
	public void removeRule(int id) {
		this.ruleDAO.removeRule(id);
	}

	@Override
	@Transactional
	public List<Rule> getRuleByName(String name) {
		return this.ruleDAO.getRuleByName(name);
	}

	@Override
	@Transactional
	public boolean addRuleElement(int id, RuleElement re) {
		return this.ruleDAO.addRuleElement(id, re);
	}

	@Override
	@Transactional
	public boolean removeRuleElement(int id, RuleElement re) {
		return this.ruleDAO.removeRuleElement(id, re);
	}

}