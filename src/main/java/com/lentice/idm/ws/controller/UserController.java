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
import com.lentice.idm.ws.model.Role;
import com.lentice.idm.ws.model.User;
import com.lentice.idm.ws.service.OrganizationService;
import com.lentice.idm.ws.service.UserService;
import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class UserController {

	private UserService userService;
	private OrganizationService orgService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserService us) {
		this.userService = us;
	}

	@Autowired(required = true)
	@Qualifier(value = "orgService")
	public void setOrgService(OrganizationService orgService) {
		this.orgService = orgService;
	}

	@GetMapping(value = "/users")
	public ResponseEntity<List<?>> getUsers() {
		List<User> users = this.userService.listUsers();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		
		if (users.isEmpty()) {
			return new ResponseEntity<List<?>>(headers, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(users, headers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/usersnew")
	public ResponseEntity<Map<String, List>> getUsersNew() {
		List<User> users = this.userService.listUsers();
		Map<String, List> map = new HashMap();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		
		if (users.isEmpty()) {
			map.put("users", null);
			return new ResponseEntity<Map<String, List>>(headers, HttpStatus.NO_CONTENT);
		}
		else {
			map.put("users", users);
			return new ResponseEntity<Map<String, List>>(map, headers, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/user/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		User user = null;
		try {
			user = this.userService.getUserById(id);
			return new ResponseEntity(user, headers, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No Customer found for ID " + id, headers, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", headers, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/users")
	public ResponseEntity<List<?>> addUsers(@Valid @RequestBody List<User> users) {
		System.out.println("#### 1 ####");
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    
		for (User user : users) {
			map.put("userLogin", user.getUserLogin());
			try {
				if(user.getKey() != 0) {
					map.put("key", ""+user.getKey());
					this.userService.updateUser(user);
					successList.add(map);
				}
				else {
					System.out.println("Will create new user: Key=>"+user.getKey()+", Name=>"+user.getUserLogin());
					this.userService.addUser(user);
					successList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			map = new HashMap<String, String>();
		}
		return new ResponseEntity<List<?>>(successList, headers, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<List<?>> deleteUser(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.userService.removeUser(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, headers, HttpStatus.OK);
	}
	
	@PutMapping(value = "/user/roles/{id}")
	public ResponseEntity<List<?>> addUserRoles(@Valid @RequestBody List<Role> roles, @PathVariable("id") int id) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json; charset=UTF-8");
	    headers.add("X-Fsl-Location", "/");
	    headers.add("Access-Control-Allow-Origin", "*");
	    headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	    headers.add("Access-Control-Allow-Credentials", "true");
	    
		try {
			Set<Role> roleSet = new HashSet<Role>();
			for(Role role : roles) {
				map.put("key", ""+role.getKey());
				roleSet.add(role);
				successList.add(map);
			}
			User user = this.userService.getUserById(id);
			user.setRoles(roleSet);
			this.userService.updateUser(user);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<?>>(successList, headers, HttpStatus.OK);
	}
}