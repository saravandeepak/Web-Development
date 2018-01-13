package com.huskyoneapp.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.huskyoneapp.dao.ProductListingDAO;
import com.huskyoneapp.entity.Address;
import com.huskyoneapp.entity.BookListing;
import com.huskyoneapp.entity.ComputerListing;
import com.huskyoneapp.entity.ElectronicsListing;
import com.huskyoneapp.entity.FurnitureListing;
import com.huskyoneapp.entity.HousingListing;
import com.huskyoneapp.entity.ProductImages;
import com.huskyoneapp.entity.ProductListing;
import com.huskyoneapp.entity.UserAccount;

@Controller
public class ProductListingController {
	
	@Autowired
	ProductListingDAO productListingDAO;
	
	@PostMapping("/postProduct")
	public String postProduct(HttpServletRequest request, MultipartRequest multipartRequest){
		
		UserAccount userAccount = (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		BookListing bookListing;
		ComputerListing computerListing;
		ElectronicsListing electronicsListing;
		FurnitureListing furnitureListing;
		
		String type = request.getParameter("productType");
		
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String condition = request.getParameter("condition");
		Float price = Float.parseFloat(request.getParameter("price"));
		
		
		Address address = new Address();
		address.setApt(request.getParameter("apt"));
		address.setStreet(request.getParameter("street"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setZipcode(Integer.parseInt(request.getParameter("zipcode")));
		
		List<ProductImages> productImageList = new ArrayList<>();
		
		List<MultipartFile> images = multipartRequest.getFiles("productimg");
		
		String path = "upload/";
		String imgUrl = "userimages/products/" + userAccount.getUserName();
		String imgPath = path + imgUrl;
		
		String id = "";
		
		if(type.equals("book")){
			bookListing = new BookListing();
			bookListing.setTitle(title);
			bookListing.setDescription(description);
			bookListing.setProductCondition(condition);
			bookListing.setPrice(price);
			bookListing.setBookname(request.getParameter("bookname"));
			bookListing.setAuthor(request.getParameter("author"));
			bookListing.setGenre(request.getParameter("genre"));
			bookListing.setIsListed(true);
			bookListing.setPostedby(userAccount);
			bookListing.setPostedDate(new Date());
			bookListing.setAddress(address);
			bookListing.setApproved("pending");
			
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
					ProductImages productImages = new ProductImages();
					productImages.setUrl("/oneapp/upload/" + imgUrl + "/" + imageFile.getName());
					productImages.setProductListingId(bookListing);
					productImageList.add(productImages);
					try
	                {
	                    multipartFile.transferTo(imageFile);
	                } catch (IOException e) 
	                {
	                    e.printStackTrace();
	                }
					
				}
			}
			bookListing.setImages(productImageList);
			productListingDAO.saveBook(bookListing);
			id = String.valueOf(bookListing.getProductListingId());
		}
		else if(type.equals("computer")){
			computerListing = new ComputerListing();
			computerListing.setTitle(title);
			computerListing.setDescription(description);
			computerListing.setProductCondition(condition);
			computerListing.setPrice(price);
			computerListing.setComputerBrand(request.getParameter("computerBrand"));
			computerListing.setComputerType(request.getParameter("computerType"));
			computerListing.setIsListed(true);
			computerListing.setPostedby(userAccount);
			computerListing.setPostedDate(new Date());
			computerListing.setAddress(address);
			computerListing.setApproved("pending");
			
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
					ProductImages productImages = new ProductImages();
					productImages.setUrl("/oneapp/upload/" + imgUrl + "/" + imageFile.getName());
					productImages.setProductListingId(computerListing);
					productImageList.add(productImages);
					try
	                {
	                    multipartFile.transferTo(imageFile);
	                } catch (IOException e) 
	                {
	                    e.printStackTrace();
	                }
					
				}
			}
			computerListing.setImages(productImageList);
			productListingDAO.saveComputer(computerListing);
			id = String.valueOf(computerListing.getProductListingId());
		}
		else if(type.equals("electronic")){
			electronicsListing = new ElectronicsListing();
			electronicsListing.setTitle(title);
			electronicsListing.setDescription(description);
			electronicsListing.setProductCondition(condition);
			electronicsListing.setPrice(price);
			electronicsListing.setElectronicMake(request.getParameter("electronicMake"));
			electronicsListing.setElectronicType(request.getParameter("electronicType"));
			electronicsListing.setIsListed(true);
			electronicsListing.setPostedby(userAccount);
			electronicsListing.setPostedDate(new Date());
			electronicsListing.setAddress(address);
			electronicsListing.setApproved("pending");
			
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
					ProductImages productImages = new ProductImages();
					productImages.setUrl("/oneapp/upload/" + imgUrl + "/" + imageFile.getName());
					productImages.setProductListingId(electronicsListing);
					productImageList.add(productImages);
					try
	                {
	                    multipartFile.transferTo(imageFile);
	                } catch (IOException e) 
	                {
	                    e.printStackTrace();
	                }
					
				}
			}
			electronicsListing.setImages(productImageList);
			productListingDAO.saveElectronic(electronicsListing);
			id = String.valueOf(electronicsListing.getProductListingId());
		}
		else if(type.equals("furniture")){
			furnitureListing = new FurnitureListing();
			furnitureListing.setTitle(title);
			furnitureListing.setDescription(description);
			furnitureListing.setProductCondition(condition);
			furnitureListing.setPrice(price);
			furnitureListing.setFurnitureType(request.getParameter("furnitureType"));
			furnitureListing.setIsListed(true);
			furnitureListing.setPostedby(userAccount);
			furnitureListing.setPostedDate(new Date());
			furnitureListing.setAddress(address);
			furnitureListing.setApproved("pending");
			
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
					ProductImages productImages = new ProductImages();
					productImages.setUrl("/oneapp/upload/" + imgUrl + "/" + imageFile.getName());
					productImages.setProductListingId(furnitureListing);
					productImageList.add(productImages);
					try
	                {
	                    multipartFile.transferTo(imageFile);
	                } catch (IOException e) 
	                {
	                    e.printStackTrace();
	                }	
				}
			}
			furnitureListing.setImages(productImageList);
			productListingDAO.saveFurniture(furnitureListing);
			id = String.valueOf(furnitureListing.getProductListingId());
		}

		return "redirect:/forsaledet.htm?id=" + id ;
	}
	
	
	@GetMapping("/forsaledet.htm")
	public ModelAndView getHousingDetails(HttpServletRequest request){
		String productId = request.getParameter("id");
		if(productId == null || productId.isEmpty()){
			return new ModelAndView("forsale");
		}
		Long prodId = Long.parseLong(productId);
		ModelAndView mav = new ModelAndView("productInfo");
		ProductListing productListing = productListingDAO.getProduct(prodId);
		mav.addObject("product", productListing);
		return mav;
	}
	
}
