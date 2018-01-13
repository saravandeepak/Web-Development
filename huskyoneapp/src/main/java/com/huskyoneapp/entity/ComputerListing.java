package com.huskyoneapp.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="computer")
public class ComputerListing extends ProductListing {
	
	@Column(name="computer_type")
	private String computerType;
	
	@Column(name="computer_brand")
	private String computerBrand;

	public ComputerListing() {
		
	}

	public String getComputerType() {
		return computerType;
	}

	public void setComputerType(String computerType) {
		this.computerType = computerType;
	}

	public String getComputerBrand() {
		return computerBrand;
	}

	public void setComputerBrand(String computerBrand) {
		this.computerBrand = computerBrand;
	}
	
	
}
