package com.huskyoneapp.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="housinglisting")
public class HousingListing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="housing_listing_id")
	private Long housingListingId;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private Float rent;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@Transient
	private List<MultipartFile> images;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="houingListingId", fetch = FetchType.EAGER)
	@Column(name="imagepath")
	@JsonManagedReference
	private List<HousingImages> imagepath;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name="startdate")
	private Date startdate;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name="enddate")
	private Date enddate;
	
	@Column
	private String type;
	
	@ManyToOne
	@JsonBackReference
	private UserAccount postedby;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name="posteddate")
	private Date posteddate;
	
	@Column(name="islisted")
	private Boolean islisted;
	
	@Column(name="approved")
	private String approved;

	public HousingListing() {

	}

	public Long getHousingListingId() {
		return housingListingId;
	}

	public void setHousingListingId(Long housingListingId) {
		this.housingListingId = housingListingId;
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

	public Float getRent() {
		return rent;
	}

	public void setRent(Float rent) {
		this.rent = rent;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<HousingImages> getImagepath() {
		return imagepath;
	}

	public void setImagepath(List<HousingImages> imagepath) {
		this.imagepath = imagepath;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public UserAccount getPostedby() {
		return postedby;
	}

	public void setPostedby(UserAccount postedby) {
		this.postedby = postedby;
	}

	public Date getPosteddate() {
		return posteddate;
	}

	public void setPosteddate(Date posteddate) {
		this.posteddate = posteddate;
	}

	public Boolean getIslisted() {
		return islisted;
	}

	public void setIslisted(Boolean islisted) {
		this.islisted = islisted;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}
	
	

	
}
