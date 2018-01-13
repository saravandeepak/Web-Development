package com.huskyoneapp.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huskyoneapp.dao.HousingListingDAO;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.ProductListing;

@RestController
public class HousingListingRestController {
	
	@Autowired
	private HousingListingDAO housingListingDAO;
	
	@RequestMapping(value = "/api/v1/getHouses", method = RequestMethod.GET, produces = "application/json")
	public Collection<HousingListing> getHouseListing(HttpServletRequest request){
		String lon = "";
		if(request.getParameter("long")!=null)
			lon = request.getParameter("long");
		String sub = "";
		if(request.getParameter("sub")!=null)
			sub = request.getParameter("sub");
		String room = "";
		if(request.getParameter("room")!=null)
			room = request.getParameter("room");
		String bnb = "";
		if(request.getParameter("bnb")!=null)
			bnb = request.getParameter("bnb");
		float minPrice = 0f;
		float maxPrice = 0f;
		if(request.getParameter("minPrice")!=null)
		  minPrice = Float.parseFloat(request.getParameter("minPrice"));
		if(request.getParameter("maxPrice")!=null)
		  maxPrice = Float.parseFloat(request.getParameter("maxPrice"));
		String searchKey = "";
		if(request.getParameter("searchKey")!=null){
			searchKey = request.getParameter("searchKey");
		}
		
		String orderby = "";
		if(request.getParameter("orderBy")!=null){
			orderby = request.getParameter("orderBy");
		}
		return housingListingDAO.getHouseListing(lon, sub, room, bnb, minPrice, maxPrice, searchKey, orderby);
		
	}
	@RequestMapping(value = "/api/v1/getAdminHouses", method = RequestMethod.GET, produces = "application/json")
	public Collection<HousingListing> getAdminHouseListing(HttpServletRequest request){
		Collection<HousingListing> houseListings =  housingListingDAO.getHouseListing("pending");
		return houseListings;
	}
}
