package com.huskyoneapp.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.huskyoneapp.service.UserAccountDetails;

@Controller
public class HousingController {
	
	@GetMapping("/housing.htm")
	public String housing(HttpServletRequest request){
		//UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "housing";
	}

}
