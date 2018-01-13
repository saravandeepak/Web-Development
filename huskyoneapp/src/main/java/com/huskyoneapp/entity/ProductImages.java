package com.huskyoneapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@DiscriminatorValue(value="productimages")
public class ProductImages extends Images{
	
	@ManyToOne
	@JoinColumn(name="product_listing_id")
	@JsonBackReference
	private ProductListing productListingId;

	public ProductImages() {

	}

	public ProductListing getProductListingId() {
		return productListingId;
	}

	public void setProductListingId(ProductListing productListingId) {
		this.productListingId = productListingId;
	}

}
