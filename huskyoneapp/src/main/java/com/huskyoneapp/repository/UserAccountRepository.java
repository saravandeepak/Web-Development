package com.huskyoneapp.repository;

import org.springframework.stereotype.Repository;

import com.huskyoneapp.entity.UserAccount;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
	
	public UserAccount findByUserName(String username);

}
