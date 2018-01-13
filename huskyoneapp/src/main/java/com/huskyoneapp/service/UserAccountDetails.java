package com.huskyoneapp.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;

import com.huskyoneapp.entity.UserAccount;

public class UserAccountDetails extends UserAccount implements UserDetails{

	private static final long serialVersionUID = 1L;
	private List<String> userRoles;
	

	public UserAccountDetails(UserAccount user,List<String> userRoles){
		super(user);
		this.userRoles=userRoles;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);			
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String getUsername() {
		return super.getUserName();
	}

}
