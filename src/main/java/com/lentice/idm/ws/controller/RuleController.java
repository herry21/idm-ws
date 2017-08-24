package com.lentice.idm.ws.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lentice.idm.ws.model.Rule;
import com.lentice.idm.ws.model.RuleElement;
import com.lentice.idm.ws.service.RuleElementService;
import com.lentice.idm.ws.service.RuleService;
import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class RuleController {

	private RuleService ruleService;
	private RuleElementService ruleElementService;

	@Autowired(required = true)
	@Qualifier(value = "ruleService")
	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@Autowired(required = true)
	@Qualifier(value = "ruleElementService")
	public void setRuleElementService(RuleElementService ruleElementService) {
		this.ruleElementService = ruleElementService;
	}

	@GetMapping(value = "/rules")
	public ResponseEntity<List<?>> getRoles() {
		List<Rule> rules = this.ruleService.listRules();
		if (rules.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(rules, HttpStatus.OK);
	}

	
	@GetMapping(value = "/rule/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {

		Rule rule = null;
		try {
			rule = this.ruleService.getRuleById(id);
			return new ResponseEntity(rule, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No Rule found for ID " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/rules")
	public ResponseEntity<List<?>> addRules(@Valid @RequestBody List<Rule> rules) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (Rule rule : rules) {
			map.put("name", rule.getName());
			try {
				if(rule.getKey() != 0) {
					map.put("key", ""+rule.getKey());
					rule.setUpdateBy("Heriyanto"); //please refine this static parameter later
					rule.setUpdateDate(new Date());
					this.ruleService.updateRule(rule);
					successList.add(map);
				}
				else {
					if(!this.ruleService.getRuleByName(rule.getName()).isEmpty()) {
						System.out.println("already exist");
					}
					else {
						rule.setCreateBy("Heriyanto"); //please refine this static parameter later
						rule.setCreateDate(new Date());
						this.ruleService.addRule(rule);
						successList.add(map);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			map = new HashMap<String, String>();
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/rules")
	public ResponseEntity<List<?>> deleteRoles(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.ruleService.removeRule(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/rule/element/{id}")
	public ResponseEntity removeRuleElement(@PathVariable("id") int id, @RequestBody RuleElement i) {
		ResponseEntity entity = null;
		try {
			if(null != i) {
				RuleElement re = this.ruleElementService.getRuleElementById(i.getKey());
				if(null != re) {
					this.ruleService.removeRuleElement(id, re);
					Rule rule = this.ruleService.getRuleById(id);
					return new ResponseEntity<Rule>(rule, HttpStatus.OK);
				}
				else {
					throw new Exception("No RuleElement found!");
				}
			}
			else {
				throw new Exception("No RuleElement key or id was provided!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
}