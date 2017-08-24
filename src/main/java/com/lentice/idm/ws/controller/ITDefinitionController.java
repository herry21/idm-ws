package com.lentice.idm.ws.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.lentice.idm.ws.model.ITDefinition;
import com.lentice.idm.ws.service.ITDefinitionService;
import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class ITDefinitionController {
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private ITDefinitionService itDefService;

	@Autowired(required = true)
	@Qualifier(value = "itDefService")
	public void setItDefService(ITDefinitionService itDefService) {
		this.itDefService = itDefService;
	}

	@GetMapping(value = "/itdefinitions")
	public ResponseEntity<List<?>> getITDefinition() {
		List<ITDefinition> itds = this.itDefService.listITResourceType();
		if (itds.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(itds, HttpStatus.OK);
	}
	
	@GetMapping(value = "/itdefinition/{id}")
	public ResponseEntity getUser(@PathVariable("id") int id) {
		ITDefinition itd = null;
		try {
			itd = this.itDefService.getITResourceTypeById(id);
			return new ResponseEntity(itd, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No ITDefinition found for ID " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/itdefinitions")
	public ResponseEntity<List<?>> addITResources(@Valid @RequestBody List<ITDefinition> itds) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (ITDefinition itd : itds) {
			map.put("name", itd.getName());
			try {
				if(itd.getKey() != 0) {
					map.put("key", ""+itd.getKey());
					itd.setUpdateDate(new Date());
					this.itDefService.updateITResourceType(itd);
					successList.add(map);
				}
				else {
					if(!this.itDefService.getITDefinitionsByName(itd.getName()).isEmpty()) {
						System.out.println("already exist");
					}
					else {
						itd.setCreateDate(new Date());
						this.itDefService.addITResourceType(itd);
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
	
	@DeleteMapping(value = "/itdefinitions")
	public ResponseEntity<List<?>> deleteOrganizations(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.itDefService.removeITResourceType(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
}