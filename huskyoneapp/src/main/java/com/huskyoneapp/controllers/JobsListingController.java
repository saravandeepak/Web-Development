package com.huskyoneapp.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.huskyoneapp.dao.JobsListingDAO;
import com.huskyoneapp.entity.JobListing;
import com.huskyoneapp.entity.UserAccount;



@Controller
public class JobsListingController {
	
	@Autowired
	private JobsListingDAO JobsListingDAO;
	
	@PostMapping("/jobPost")
	public String postJob(@ModelAttribute("jobListing") JobListing jobListing){
		
		UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		jobListing.setPosteddate(new Date());
		jobListing.setPostedby(userAccount);
		
		JobsListingDAO.saveOrUpdate(jobListing);
		
		return "index";
		
	}
	
	
}
