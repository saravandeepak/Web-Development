package com.huskyoneapp.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="electronics")
public class ElectronicsListing extends ProductListing {
	
	@Column(name="electronic_type")
	private String electronicType;
	
	@Column(name="electronic_make")
	private String electronicMake;

	public ElectronicsListing() {
	
	}

	public String getElectronicType() {
		return electronicType;
	}

	public void setElectronicType(String electronicType) {
		this.electronicType = electronicType;
	}

	public String getElectronicMake() {
		return electronicMake;
	}

	public void setElectronicMake(String electronicMake) {
		this.electronicMake = electronicMake;
	}
	
	
	
	

}
