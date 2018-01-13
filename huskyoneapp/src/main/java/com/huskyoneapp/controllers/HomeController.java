package com.huskyoneapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.huskyoneapp.entity.Address;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.JobListing;

@Controller
public class HomeController {
	
	@GetMapping(value= {"/", "home.htm"})
	public String index(){
		return "index";
	}
	
	@GetMapping(value= "/post.htm")
	public String login(Model model){
		HousingListing housingListing = new HousingListing();
		housingListing.setAddress(new Address());
		model.addAttribute("housingListing", housingListing);
		JobListing jobListing = new JobListing();
		model.addAttribute("jobListing", jobListing);
		return "postListing";
	}

}
