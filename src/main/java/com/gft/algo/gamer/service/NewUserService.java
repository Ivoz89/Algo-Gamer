package com.gft.algo.gamer.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.repository.AccountRepository;
@Service
public class NewUserService {
	@Autowired
	AccountRepository accountRepository;
	 final Logger logger = LoggerFactory.getLogger(NewUserService.class);
	private boolean checkIfUserExists(String username) {
		if (accountRepository.exists(username))
			return true;
		else
			return false;

	}
public boolean addNewUser(String username, String password) 
{
	if(checkIfUserExists(username) == false)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Account account = new Account(username,encoder.encode(password));
		accountRepository.save(account);
		accountRepository.flush();
		logger.info("User Added Succesfully");
		return true;
	}
	else{
			
	logger.error("Username Exists");
	return false;
	}
	}
	



}
