package com.lentice.idm.ws.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lentice.idm.ws.dao.RuleElementDAO;
import com.lentice.idm.ws.model.RuleElement;
import com.lentice.idm.ws.service.RuleElementService;

@Service
public class RuleElementServiceImpl implements RuleElementService {
	
	private RuleElementDAO ruleElementDAO;

	public void setRuleElementDAO(RuleElementDAO ruleElementDAO) {
		this.ruleElementDAO = ruleElementDAO;
	}

	@Override
	@Transactional
	public void addRuleElement(RuleElement re) {
		this.ruleElementDAO.addRuleElement(re);
	}

	@Override
	@Transactional
	public void updateRuleElement(RuleElement re) {
		this.ruleElementDAO.updateRuleElement(re);
	}

	@Override
	@Transactional
	public List<RuleElement> listRuleElements() {
		return this.ruleElementDAO.listRuleElements();
	}

	@Override
	@Transactional
	public RuleElement getRuleElementById(int id) {
		return this.ruleElementDAO.getRuleElementById(id);
	}

	@Override
	@Transactional
	public void removeRuleElement(int id) {
		this.ruleElementDAO.removeRuleElement(id);
	}

	@Override
	@Transactional
	public List<RuleElement> getRuleElementByName(String name) {
		return this.ruleElementDAO.getRuleElementByName(name);
	}
}