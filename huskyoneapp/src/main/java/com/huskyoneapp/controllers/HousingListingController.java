package com.huskyoneapp.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.huskyoneapp.dao.HousingListingDAO;

import com.huskyoneapp.entity.HousingImages;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.UserAccount;

@Controller
public class HousingListingController {
	
	@Autowired
	private HousingListingDAO housingListingDAO;
	
	@Autowired
    private Environment env;
	
	@PostMapping("/postHousing")
	public String postHousing(@ModelAttribute("housingListing") HousingListing housingListing, MultipartRequest multipartRequest, HttpServletRequest request){
		
		UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		//get all the uploaded files from multipart request
		List<MultipartFile> images = multipartRequest.getFiles("housingimg");
		//set it to the transient attribute
		housingListing.setImages(images);
		
		List<HousingImages> housingImageList = new ArrayList<HousingImages>();
		//get the path and append username 
		String path = "upload/";
		String imgUrl = "userimages/housing/" + userAccount.getUserName();
		String imgPath = path + imgUrl;
		if(null != images && images.size() > 0 && !images.get(0).getOriginalFilename().isEmpty()) {
			for (MultipartFile multipartFile : images) {
				//get filename of the file
				String fileName = multipartFile.getOriginalFilename();
				
				
				//create a directory if it doesn't already exist
				File imageDir = new File(imgPath);
				if (!imageDir.exists()) {
			        imageDir.mkdirs();     
				}
				
				//create the file
				File imageFile = new File(imageDir.getAbsolutePath(), fileName);
				
				//add 
				HousingImages housingImages = new HousingImages();
				housingImages.setUrl("/oneapp/upload/" + imgUrl + "/" + imageFile.getName());
				housingImages.setHouingListingId(housingListing);
				housingImageList.add(housingImages);
				try
                {
                    multipartFile.transferTo(imageFile);
                } catch (IOException e) 
                {
                    e.printStackTrace();
                }
				
			}
		}
		
		String housingType = request.getParameter("housingType");
		
		housingListing.setType(housingType);
		housingListing.setImagepath(housingImageList);
		housingListing.setIslisted(true);
		housingListing.setPostedby(userAccount);
		Calendar calendar = Calendar.getInstance();
		housingListing.setPosteddate((calendar.getTime()));
		housingListing.setApproved("pending");
		
		
		housingListingDAO.saveOrUpdate(housingListing);
		String id = String.valueOf(housingListing.getHousingListingId());
		
		return "redirect:housingdetails.htm?id=" + id;
	}
		
	@GetMapping("/housingdetails.htm")
	public ModelAndView getHousingDetails(HttpServletRequest request){
		String houseId = request.getParameter("id");
		if(houseId == null || houseId.isEmpty()){
			return new ModelAndView("housing");
		}
		Long housingId = Long.parseLong(houseId);
		ModelAndView mav = new ModelAndView("houseInfo");
		HousingListing housingListing = housingListingDAO.getHouse(housingId);
		mav.addObject("house", housingListing);
		return mav;
	}
	
	
	

}
