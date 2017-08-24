package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.Rule;
import com.lentice.idm.ws.model.RuleElement;

public interface RuleDAO {

	public void addRule(Rule i);
	public void updateRule(Rule i);
	public List<Rule> listRules();
	public List<Rule> getRuleByName(String name);
	public Rule getRuleById(int id);
	public void removeRule(int id);
	public boolean addRuleElement(int id, RuleElement re);
	public boolean removeRuleElement(int id, RuleElement re);
}