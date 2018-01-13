package com.huskyoneapp.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huskyoneapp.entity.UserAccount;

@Repository
public class UserAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
    public void saveOrUpdate(UserAccount userAccount) {
        sessionFactory.getCurrentSession().saveOrUpdate(userAccount);
    }
	
	@Transactional
    public UserAccount getByUserName(String username) {
        Query query = sessionFactory.getCurrentSession().createQuery("from UserAccount where username = :username");
        query.setString("username", username);
        UserAccount userAccount = (UserAccount) query.uniqueResult();
        return userAccount;
    }
	
	
}
