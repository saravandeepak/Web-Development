package com.huskyoneapp.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.huskyoneapp.dao.UserAccountDAO;
import com.huskyoneapp.dao.UserRolesDAO;
import com.huskyoneapp.entity.UserAccount;
import com.huskyoneapp.entity.UserRoles;
import com.huskyoneapp.validators.LoginValidator;

@Controller
public class LoginController {

	@Autowired
	private UserAccountDAO userAccountDAO;
	
	@Autowired 
	private UserRolesDAO userRolesDAO;
	
	@Autowired
    private LoginValidator loginValidator;
	
	@GetMapping(value= "/login")
	public String login(Model model){
		model.addAttribute("userAccount", new UserAccount());
		return "login";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("userAccount") UserAccount userAccount, BindingResult result, HttpServletRequest request){

		loginValidator.validate(userAccount, result);
		if(result.hasErrors()){
			request.getSession().setAttribute("isRegister", "true");
			request.getSession().setAttribute("regiserError", "true");
			request.getSession().setAttribute("isSuccess", null);
			return "login";
		}
		request.getSession().setAttribute("isRegister", null);
		request.getSession().setAttribute("regiserError", null);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(encoder.encode(userAccount.getPassword()));
		userAccountDAO.saveOrUpdate(userAccount);
		UserRoles userRoles = new UserRoles();
		userRoles.setRole("ROLE_USER");
		userRoles.setUserId(userAccount.getUserId());
		userRolesDAO.saveOrUpdate(userRoles);
		request.getSession().setAttribute("isSuccess", "true");
		
		return "redirect:/login";
	}
	
	@PostMapping("/reg")
	public String registerPost(HttpServletRequest request){
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		if(userAccountDAO.getByUserName(userName)!=null){
			request.getSession().setAttribute("isRegister", "true");
			request.getSession().setAttribute("regiserError", "true");
			request.getSession().setAttribute("isSuccess", null);
			return "login";
		}
		request.getSession().setAttribute("isRegister", null);
		request.getSession().setAttribute("regiserError", null);
		UserAccount userAccount = new UserAccount();
		userAccount.setUserName(userName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAccount.setPassword(encoder.encode(password));
		userAccount.setEmail(email);
		userAccountDAO.saveOrUpdate(userAccount);
		
		UserRoles userRoles = new UserRoles();
		userRoles.setRole("ROLE_USER");
		userRoles.setUserId(userAccount.getUserId());
		userRolesDAO.saveOrUpdate(userRoles);
		request.getSession().setAttribute("isSuccess", "true");
		
		return "login";
	}
	
	@GetMapping("/register")
	public String registerGet(HttpServletRequest request){
		return "login";
	}

}
