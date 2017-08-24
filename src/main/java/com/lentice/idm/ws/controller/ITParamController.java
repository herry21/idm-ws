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
import com.lentice.idm.ws.model.ITParam;
import com.lentice.idm.ws.service.ITParamService;

import java.util.stream.Collectors;

@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
@RestController
public class ITParamController {

	private ITParamService itParamService;

	@Autowired(required = true)
	@Qualifier(value = "itParamService")
	public void setItParamService(ITParamService itParamService) {
		this.itParamService = itParamService;
	}

	@GetMapping(value = "/itparams")
	public ResponseEntity<List<?>> getITParams() {
		List<ITParam> itps = this.itParamService.listITParams();
		if (itps.isEmpty()) {
			return new ResponseEntity<List<?>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<?>>(itps, HttpStatus.OK);
	}
	
	@GetMapping(value = "/itparam/{id}")
	public ResponseEntity getItParam(@PathVariable("id") int id) {
		ITParam itps = null;
		try {
			itps = this.itParamService.getITParamById(id);
			return new ResponseEntity(itps, HttpStatus.OK);
		} catch (ObjectNotFoundException one) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("No ITParam found for ID " + id, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			System.out.println("hasilnya tidak ada");
			return new ResponseEntity("Error occured while executing your command", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/itparams")
	public ResponseEntity<List<?>> addITParams(@Valid @RequestBody List<ITParam> itps) {
		List<Map<String, String>> successList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		for (ITParam itp : itps) {
			try {
				if(itp.getKey() != 0) {
					map.put("key", ""+itp.getKey());
					this.itParamService.updateITParam(itp);
					successList.add(map);
				}
				else {
					this.itParamService.addITParam(itp);
					successList.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			map = new HashMap<String, String>();
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/itparams")
	public ResponseEntity<List<?>> deleteOrganizations(@Valid @RequestBody List<Map> ids) {
		List<Map> successList = new ArrayList<Map>();
		for (Map map : ids) {
			try {
				int key = Integer.parseInt(map.get("key").toString());
				this.itParamService.removeITParam(key);
				successList.add(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<List<?>>(successList, HttpStatus.OK);
	}
}