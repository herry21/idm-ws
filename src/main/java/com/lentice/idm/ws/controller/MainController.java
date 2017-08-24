package com.lentice.idm.ws.controller;

//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.lentice.dev.model.Organization;
//import com.lentice.dev.model.User;
//import com.lentice.dev.service.OrganizationService;
//import com.lentice.dev.service.UserService;

@Controller
public class MainController {	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}
}