package com.huskyoneapp.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class UserAccount{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Long userId;

	
	@Column(name="username")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="postedby")
	@JsonManagedReference
	private List<HousingListing> houseposts;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="postedby")
	@JsonManagedReference
	private List<JobListing> jobposts;

	public UserAccount(){
		
	}
	
	public UserAccount(UserAccount user){
        this.userId = user.userId;
        this.userName = user.userName;
        this.email = user.email;       
        this.password = user.password;
      
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HousingListing> getHouseposts() {
		return houseposts;
	}

	public void setHouseposts(List<HousingListing> houseposts) {
		this.houseposts = houseposts;
	}
	
	
	
}
