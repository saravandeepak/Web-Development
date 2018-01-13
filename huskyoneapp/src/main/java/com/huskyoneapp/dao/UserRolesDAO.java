package com.huskyoneapp.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huskyoneapp.entity.UserRoles;

@Repository
public class UserRolesDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
    public void saveOrUpdate(UserRoles userRoles) {
        sessionFactory.getCurrentSession().saveOrUpdate(userRoles);
    }
	

}
