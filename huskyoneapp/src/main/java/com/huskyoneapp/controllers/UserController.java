package com.huskyoneapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.ProductListing;
import com.huskyoneapp.entity.UserAccount;
import com.huskyoneapp.repository.HousingListingRepository;
import com.huskyoneapp.repository.ProductListingRepository;
import com.huskyoneapp.service.UserAccountDetails;

@RestController
public class UserController {
	
	@Autowired
	private HousingListingRepository housingListingRepository;
	
	@Autowired
	private ProductListingRepository productListingRepository;
	
	@RequestMapping(value = "/api/v1/getUserHouses", method=RequestMethod.GET)
	public List<HousingListing> getHouses(HttpServletRequest request){
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAccount userAccount = user;
		List<HousingListing> housingListings = housingListingRepository.findByPostedby(userAccount);
		return housingListings;
		
	}
	
	@RequestMapping(value = "/api/v1/getUserProduct", method=RequestMethod.GET)
	public List<ProductListing> getProduct(HttpServletRequest request){
		UserAccountDetails user = (UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAccount userAccount = user;
		List<ProductListing> productListings = productListingRepository.findByPostedby(userAccount);
		return productListings;
	}

}
