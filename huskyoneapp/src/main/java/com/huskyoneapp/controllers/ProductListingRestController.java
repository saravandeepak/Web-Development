package com.huskyoneapp.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huskyoneapp.dao.ProductListingDAO;
import com.huskyoneapp.entity.ProductListing;

@RestController
public class ProductListingRestController {
	
	@Autowired
	private ProductListingDAO productListingDAO;
	
	@RequestMapping(value = "/api/v1/getProducts", method = RequestMethod.GET, produces = "application/json")
	public Collection<ProductListing> getProducts(HttpServletRequest request){
		String type = request.getParameter("productType");
		String newc = "";
		String unpacked =  "";
		String excellent = "";
		String fair = "";
		String old = "";
		Collection<String> condition = new ArrayList<>();
		if(request.getParameter("newc")!= null){
			newc = request.getParameter("newc");
			condition.add(newc);
		}
		if(request.getParameter("unpacked")!= null){
			unpacked = request.getParameter("unpacked");
			condition.add(unpacked);
		}
		if(request.getParameter("excellent")!= null){
			excellent = request.getParameter("excellent");
			condition.add(excellent);
		}
		if(request.getParameter("fair")!= null){
			fair = request.getParameter("fair");
			condition.add(fair);
		}
		if(request.getParameter("old")!= null){
			old = request.getParameter("old");
			condition.add(old);
		}

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
		
		Collection<ProductListing> productListings = productListingDAO.getProductsListing(type, minPrice, maxPrice, condition, searchKey, orderby);
		return productListings;
	}
	
	@RequestMapping(value = "/api/v1/getAdminProducts", method = RequestMethod.GET, produces = "application/json")
	public Collection<ProductListing> getAdminProducts(HttpServletRequest request){
		Collection<ProductListing> productListings = productListingDAO.getProductsListing("pending");
		return productListings;
	}
}
