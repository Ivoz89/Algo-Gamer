/*package com.gft.algo.gamer.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.AccountDTO;
import com.gft.algo.gamer.model.Asset;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.model.StockData;
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
	   @RequestMapping(value = "/users/{login}", method = RequestMethod.GET)
	    public Account get(@PathVariable("login") String login) throws DataAccessException  {
		   Account account = accountRepo.findOne("Wosin");
			StockData stockData = dataDownloadService.downloadNewStock("MSFT");
			StockData stockData1 = dataDownloadService.downloadNewStock("FB");
			Portfolio portfolio = new Portfolio();
			Portfolio portoflio1 = new Portfolio();
			portfolio.setName("Microsoft");
			portoflio1.setName("Fejsbuk");
			Set<Asset> assets = new HashSet<>();
			Set<Asset> assets1 = new HashSet<>();
			Asset asset = new Asset(stockData, 100, portfolio);
			Asset asset1 = new Asset(stockData1, 1001, portoflio1);
			assets.add(asset);
			assets1.add(asset1);
			portfolio.setAssets(assets);
			portoflio1.setAssets(assets1);
			List<Portfolio> portfolios = new ArrayList<>();
			portfolios.add(portoflio1);
			portfolios.add(portfolio);
			portfolioRepo.save(portfolio);
			portfolioRepo.save(portoflio1);
			portfolioRepo.flush();
			assetRepo.save(asset);
			assetRepo.save(asset1);

			assetRepo.flush();

			account.setPortfolioList(portfolios);
			accountRepo.saveAndFlush(account);
			return accountRepo.findByIdAndFetchAlgorithmsEagerly("Wosin");
	    }
}
*/