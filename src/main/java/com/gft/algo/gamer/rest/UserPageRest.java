package com.gft.algo.gamer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.algo.gamer.model.AccountDTO;
import com.gft.algo.gamer.repository.AccountRepository;
@RestController
public class UserPageRest {
	@Autowired
	AccountRepository accountRepo;
	   @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
	    public AccountDTO get(@PathVariable("login") String login)  {
		 AccountDTO accountDTO = accountRepo.GetDTOforLogin("Wosin");

		 accountDTO.setPortfolioList(accountRepo.GetPortfolios("Wosin"));
		   
	       return 	accountDTO;    }
}
