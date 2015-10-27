package com.gft.algo.gamer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gft.algo.gamer.config.Application;
import com.gft.algo.gamer.repository.AccountRepository;

import junit.framework.Assert;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})

public class AddingUserTest extends TestCase {
	@Autowired
	NewUserService newUserService;
	@Autowired
	AccountRepository accountRepository;
	
@Test
	    public void testIfCantDuplicateUser() {
		
	Assert.assertFalse(newUserService.addNewUser("Wosin", "password"));
		
	  
	    }
		 @Test
	    public void testIfCanAddUser() {
		 
				newUserService.addNewUser("Michal", "haslo");
				
				 
				 Assert.assertTrue(accountRepository.exists("Michal"));
		}
		 
		 
	  
	    }
	 

