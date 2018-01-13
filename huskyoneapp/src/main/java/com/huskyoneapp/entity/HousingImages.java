package com.huskyoneapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue(value="housingimages")
public class HousingImages extends Images {
	
	@ManyToOne
	@JoinColumn(name="housing_listing_id")
	@JsonBackReference
	private HousingListing houingListingId;

	public HousingImages() {
	}

	public HousingListing getHouingListingId() {
		return houingListingId;
	}

	public void setHouingListingId(HousingListing houingListingId) {
		this.houingListingId = houingListingId;
	}

}
