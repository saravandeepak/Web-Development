package com.huskyoneapp.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="furniture")
public class FurnitureListing extends ProductListing {
	
	@Column(name="furniture_type")
	private String furnitureType;

	public FurnitureListing() {

	}

	public String getFurnitureType() {
		return furnitureType;
	}

	public void setFurnitureType(String furnitureType) {
		this.furnitureType = furnitureType;
	}
	
	

}
