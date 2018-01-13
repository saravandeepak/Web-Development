package com.huskyoneapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.huskyoneapp.entity.UserAccount;
import com.huskyoneapp.repository.UserAccountRepository;
import com.huskyoneapp.repository.UserRolesRepository;

@Service
public class UserAccountService implements UserDetailsService {
	
	private final UserAccountRepository userAccountRepository;
	private final UserRolesRepository userRolesRepository;
	
	@Autowired
    public UserAccountService(UserAccountRepository userAccountRepository,UserRolesRepository userRolesRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRolesRepository=userRolesRepository;
    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount user = userAccountRepository.findByUserName(username);
		if(user == null){
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user present with username: "+username);
		}
		else{
			List<String> userRoles = userRolesRepository.findRoleByUserName(username);
			return new UserAccountDetails(user, userRoles);
		}
	}

}
