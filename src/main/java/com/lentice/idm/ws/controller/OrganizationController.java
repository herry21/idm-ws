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
import org.springframework.http.HttpHeaders;
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
import com.lentice.idm.ws.service.OrganizationService;
import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class OrganizationController {

	private OrganizationService orgService;

	@Autowired(required = true)
	@Qualifier(value = "orgService")
	public void setOrgService(OrganizationService orgService) {
		this.orgService = orgService;
	}

	@GetMapping(value = "/organizations")
	public ResponseEntity<List<?>> getOrganizations() {
		List<Organization> orgs = this.orgService.listOrganizations();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
		
		if (orgs.isEmpty()) {
			return new ResponseEntity<List<?>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(orgs, headers, HttpStatus.OK);
	}

	
	@GetMapping(value = "/organization/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {

		Organization org = null;
		try {
			org = this.orgService.getOrganizationById(id);
			return new ResponseEntity(org, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No Organization found for ID " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/organizations")
	public ResponseEntity<List<?>> addOrganizations(@Valid @RequestBody List<Organization> orgs) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (Organization org : orgs) {
			map.put("name", org.getName());
			try {
				if(org.getKey() != 0) {
					map.put("key", ""+org.getKey());
					this.orgService.updateOrganization(org);
					successList.add(map);
				}
				else {
					if(!this.orgService.getOrganizationByName(org.getName()).isEmpty()) {
						System.out.println("already exist");
					}
					else {
						this.orgService.addOrganization(org);
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
	
	@DeleteMapping(value = "/organizations")
	public ResponseEntity<List<?>> deleteOrganizations(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.orgService.removeOrganization(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
}