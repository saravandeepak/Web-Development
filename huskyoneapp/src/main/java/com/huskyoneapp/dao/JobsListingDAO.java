package com.huskyoneapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huskyoneapp.entity.JobListing;

@Repository
public class JobsListingDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
    public void saveOrUpdate(JobListing jobListing) {
        sessionFactory.getCurrentSession().saveOrUpdate(jobListing);
    }

}
