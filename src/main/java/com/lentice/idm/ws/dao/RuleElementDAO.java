package com.lentice.idm.ws.dao;

import java.util.List;

import com.lentice.idm.ws.model.RuleElement;

public interface RuleElementDAO {

	public void addRuleElement(RuleElement re);
	public void updateRuleElement(RuleElement re);
	public List<RuleElement> listRuleElements();
	public List<RuleElement> getRuleElementByName(String name);
	public RuleElement getRuleElementById(int id);
	public void removeRuleElement(int id);
}