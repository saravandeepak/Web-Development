package com.huskyoneapp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


//Single table inheritance
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="productlisting")
@DiscriminatorColumn(name = "producttype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("product")
public class ProductListing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_listing_id")
	private Long productListingId;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private Float price;
	
	@Column(name="product_condition")
	private String productCondition;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="productListingId", fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<ProductImages> images;
	
	@Transient
	private List<MultipartFile> prodImages;
	
	@ManyToOne
	@JsonBackReference
	private UserAccount postedby;
	
	@Temporal(TemporalType.DATE)
	@Column(name="posteddate")
	private Date postedDate;
	
	@Column(name="islisted")
	private Boolean isListed;
	
	@Column(name="approved")
	private String approved;

	public ProductListing() {

	}

	public Long getProductListingId() {
		return productListingId;
	}

	public void setProductListingId(Long productListingId) {
		this.productListingId = productListingId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getProductCondition() {
		return productCondition;
	}

	public void setProductCondition(String productCondition) {
		this.productCondition = productCondition;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ProductImages> getImages() {
		return images;
	}

	public void setImages(List<ProductImages> images) {
		this.images = images;
	}

	public UserAccount getPostedby() {
		return postedby;
	}

	public void setPostedby(UserAccount postedby) {
		this.postedby = postedby;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public Boolean getIsListed() {
		return isListed;
	}

	public void setIsListed(Boolean isListed) {
		this.isListed = isListed;
	}

	public List<MultipartFile> getProdImages() {
		return prodImages;
	}

	public void setProdImages(List<MultipartFile> prodImages) {
		this.prodImages = prodImages;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}
	
	
	
}
