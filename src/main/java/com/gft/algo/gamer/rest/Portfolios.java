package com.gft.algo.gamer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.repository.AccountRepository;
import com.gft.algo.gamer.repository.AssetRepository;
import com.gft.algo.gamer.repository.PortfolioRepository;
import com.gft.algo.gamer.service.DataAccessException;
import com.gft.algo.gamer.service.DataDownloadService;
@RestController
public class Portfolios {
	@Autowired 
	AccountRepository accountRepo;
	@Autowired
	DataDownloadService dataDownloadService;
	@Autowired 
	PortfolioRepository portfolioRepo;
	@Autowired 
	AssetRepository assetRepo;
	   @RequestMapping(value = "/portfolio/{login}", method = RequestMethod.GET)
	    public List<Portfolio> getPortfolios(@PathVariable("login") String login) throws DataAccessException  {
		   return portfolioRepo.GetPortfoliosforUser(login);
	    }
	   @RequestMapping(value = "/portfolio/{login}/{name}", method = RequestMethod.POST)
	    public Portfolio getPortfolios(@PathVariable("login") String login,@PathVariable("name") String name) throws DataAccessException  {
		 Portfolio portfolio = new Portfolio();
		 Account account = accountRepo.GetEagerPOrtfolio(login);
		 List<Portfolio> portfolios = account.getPortfolioList();
		
		 portfolio.setAccount(account);
		 portfolio.setName(name);
		
		 portfolios.add(portfolio);
		 account.setPortfolioList(portfolios);
		accountRepo.saveAndFlush(account);
		 return portfolio;
	    }
}
