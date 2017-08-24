package com.lentice.idm.ws.controller;

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
import com.lentice.idm.ws.model.Organization;
import com.lentice.idm.ws.model.Role;
import com.lentice.idm.ws.model.Rule;
import com.lentice.idm.ws.service.RoleService;
import com.lentice.idm.ws.service.RuleService;

import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class RoleController {

	private RoleService roleService;
	private RuleService ruleService;

	@Autowired(required = true)
	@Qualifier(value = "roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired(required = true)
	@Qualifier(value = "ruleService")
	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

	@GetMapping(value = "/roles")
	public ResponseEntity<List<?>> getRoles() {
		List<Role> roles = this.roleService.listRoles();
		if (roles.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(roles, HttpStatus.OK);
	}

	
	@GetMapping(value = "/role/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {

		Role role = null;
		try {
			role = this.roleService.getRoleById(id);
			return new ResponseEntity(role, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/roles")
	public ResponseEntity<List<?>> addRoles(@Valid @RequestBody List<Role> roles) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (Role role : roles) {
			map.put("name", role.getName());
			try {
				if(role.getKey() != 0) {
					map.put("key", ""+role.getKey());
					Set<Rule> rules = new HashSet<Rule>();
					for(Rule rule : role.getRules()) {
						Rule getr = this.ruleService.getRuleById(rule.getKey());
						if(getr != null) {
							rules.add(getr);
						}
					}
					role.setRules(rules);
					this.roleService.updateRole(role);
					successList.add(map);
				}
				else {
					if(!this.roleService.getRoleByName(role.getName()).isEmpty()) {
						System.out.println("already exist");
					}
					else {
						this.roleService.addRole(role);
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
	
	@DeleteMapping(value = "/roles")
	public ResponseEntity<List<?>> deleteRoles(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.roleService.removeRole(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
}