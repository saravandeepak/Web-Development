package com.huskyoneapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "job_listing")
public class JobListing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobid;
	
	@Column
	private String jobtitle;
	
	@Column
	private String description;
	
	@Column
	private String qualifications;
	
	@Column
	private String location;
	
	@Column
	private String applylink;
	
	@Temporal(TemporalType.DATE)
	@Column(name="posteddate")
	private Date posteddate;
	
	@ManyToOne
	private UserAccount postedby;

	public JobListing() {

	}

	public long getJobid() {
		return jobid;
	}

	public void setJobid(long jobid) {
		this.jobid = jobid;
	}

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getApplylink() {
		return applylink;
	}

	public void setApplylink(String applylink) {
		this.applylink = applylink;
	}

	public Date getPosteddate() {
		return posteddate;
	}

	public void setPosteddate(Date posteddate) {
		this.posteddate = posteddate;
	}

	public UserAccount getPostedby() {
		return postedby;
	}

	public void setPostedby(UserAccount postedby) {
		this.postedby = postedby;
	}

	
	
	

}
