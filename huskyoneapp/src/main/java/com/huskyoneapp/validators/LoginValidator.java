package com.huskyoneapp.validators;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.huskyoneapp.dao.UserAccountDAO;
import com.huskyoneapp.entity.UserAccount;

@Component
public class LoginValidator implements Validator {
	
	@Autowired
	private UserAccountDAO userAccountDAO;

	@Override
	public boolean supports(Class<?> clas) {
		
		return UserAccount.class.isAssignableFrom(clas);
	}

	@Override
	public void validate(Object obj, Errors e) {
		UserAccount user = (UserAccount) obj;
		if(userAccountDAO.getByUserName(user.getUserName())!=null){
			e.rejectValue("userName", "error.userName", "Username is already taken");
		}
		
	}


}
