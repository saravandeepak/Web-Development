package com.huskyoneapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.huskyoneapp.dao.HousingListingDAO;
import com.huskyoneapp.dao.ProductListingDAO;
import com.huskyoneapp.dao.UserAccountDAO;
import com.huskyoneapp.repository.HousingListingRepository;
import com.huskyoneapp.repository.ProductListingRepository;
import com.huskyoneapp.repository.UserAccountRepository;

@RestController
public class AdminController {
	
	@Autowired
	private ProductListingDAO productListingDAO;
	
	@Autowired 
	private HousingListingDAO housingListingDAO;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private HousingListingRepository housingListingRepository;
	
	@Autowired
	private ProductListingRepository productListingRepository;

	@RequestMapping(value = "/api/v1/approveProduct", method=RequestMethod.GET)
	public void approveProduct(HttpServletRequest request){
		String productId = request.getParameter("productId");
		productListingDAO.approveProductListing(Long.parseLong(productId));
	}
	
	@RequestMapping(value = "/api/v1/approveHouse", method=RequestMethod.GET)
	public void approveHouse(HttpServletRequest request){
		String houseId = request.getParameter("houseId");
		housingListingDAO.approveHouseListing(Long.parseLong(houseId));
	}
	
	@RequestMapping(value = "/api/v1/rejectProduct", method=RequestMethod.GET)
	public void rejectProduct(HttpServletRequest request){
		String productId = request.getParameter("productId");
		productListingDAO.rejectProductListing(Long.parseLong(productId));
	}
	
	@RequestMapping(value = "/api/v1/rejectHouse", method=RequestMethod.GET)
	public void rejectHouse(HttpServletRequest request){
		String houseId = request.getParameter("houseId");
		housingListingDAO.rejectHouseListing(Long.parseLong(houseId));
	}
	
	@RequestMapping(value = "/api/v1/getStats", method=RequestMethod.GET, produces = "application/json")
	public List<Long> getStats(){
		long noofusers = userAccountRepository.count(); 
    	long noofhouselistings = housingListingRepository.count();
		long noofproductlistings = productListingRepository.count();
    	long houseapproveno = housingListingRepository.countByApproved("approved");
    	long housependingno = housingListingRepository.countByApproved("pending");
    	long houserejectno = housingListingRepository.countByApproved("rejected");
    	long productapproveno = productListingRepository.countByApproved("approved");
    	long productpendingno = productListingRepository.countByApproved("pending");
    	long productrejectno = productListingRepository.countByApproved("rejected");
    	
    	List<Long> result = new ArrayList<>();
    	result.add(noofusers);
    	result.add(noofhouselistings);
    	result.add(noofproductlistings);
    	result.add(houseapproveno);
    	result.add(housependingno);
    	result.add(houserejectno);
    	result.add(productapproveno);
    	result.add(productpendingno);
    	result.add(productrejectno);
    	return result;
	}
	
	
	
}
