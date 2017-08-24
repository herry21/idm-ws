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

import com.lentice.idm.ws.model.ITResource;
import com.lentice.idm.ws.service.ITResourceService;
import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class ITResourceController {

	private ITResourceService itrService;

	@Autowired(required = true)
	@Qualifier(value = "itrService")
	public void setItrService(ITResourceService itrService) {
		this.itrService = itrService;
	}

	@GetMapping(value = "/itresources")
	public ResponseEntity<List<?>> getITResources() {
		List<ITResource> itrs = this.itrService.listITResources();
		if (itrs.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(itrs, HttpStatus.OK);
	}

	
	@GetMapping(value = "/itresource/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {

		ITResource itr = null;
		try {
			itr = this.itrService.getITResourceById(id);
			return new ResponseEntity(itr, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No ITResource found for ID " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/itresources")
	public ResponseEntity<List<?>> addITResources(@Valid @RequestBody List<ITResource> itrs) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (ITResource itr : itrs) {
			map.put("name", itr.getName());
			try {
				if(itr.getKey() != 0) {
					map.put("key", ""+itr.getKey());
					this.itrService.updateITResource(itr);
					successList.add(map);
				}
				else {
					if(!this.itrService.getITResourceByName(itr.getName()).isEmpty()) {
						System.out.println("already exist");
					}
					else {
						this.itrService.addITResource(itr);
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
	
	@DeleteMapping(value = "/itresources")
	public ResponseEntity<List<?>> deleteOrganizations(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.itrService.removeITResource(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
}