//package com.gft.algo.gamer.service;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.gft.algo.gamer.config.Application;
//import com.gft.algo.gamer.model.Account;
//import com.gft.algo.gamer.model.Asset;
//import com.gft.algo.gamer.model.Portfolio;
//import com.gft.algo.gamer.model.StockData;
//import com.gft.algo.gamer.repository.AccountRepository;
//import com.gft.algo.gamer.repository.AssetRepository;
//import com.gft.algo.gamer.repository.PortfolioRepository;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Application.class})
//public class PortfolioTest {
//
//	@Autowired 
//	AccountRepository accountRepo;
//	@Autowired
//	DataDownloadService dataDownloadService;
//	@Autowired 
//	PortfolioRepository portfolioRepo;
//	@Autowired 
//	AssetRepository assetRepo;
//	@Before
//	public void   init() throws DataAccessException
//	{
//	Account account = accountRepo.findOne("Wosin");
//		StockData stockData = dataDownloadService.downloadNewStock("MSFT");
//		StockData stockData1 = dataDownloadService.downloadNewStock("FB");
//		Portfolio portfolio = new Portfolio();
//		Portfolio portoflio1 = new Portfolio();
//		portfolio.setName("Microsoft");
//		portoflio1.setName("Fejsbuk");
//		Set<Asset> assets = new HashSet<>();
//		Set<Asset> assets1 = new HashSet<>();
//		Asset asset = new Asset(stockData, 100, portfolio);
//		Asset asset1 = new Asset(stockData1, 1001, portoflio1);
//		assets.add(asset);
//		assets1.add(asset1);
//		portfolio.setAssets(assets);
//		portoflio1.setAssets(assets1);
//		List<Portfolio> portfolios = new ArrayList<>();
//		portfolios.add(portoflio1);
//		portfolios.add(portfolio);
//		portfolioRepo.save(portfolio);
//		portfolioRepo.save(portoflio1);
//		portfolioRepo.flush();
//		assetRepo.save(asset);
//		assetRepo.save(asset1);
//
//		assetRepo.flush();
//
//		account.setPortfolioList(portfolios);
//		accountRepo.saveAndFlush(account);
//	}
//	@Test
//	public void plisWork()
//	{
//		System.out.println(accountRepo.findByIdAndFetchAlgorithmsEagerly("Wosin").toString());
//		
//	}
//}
