package com.gft.algo.gamer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gft.algo.gamer.config.Application;
import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.model.StockData;
import com.gft.algo.gamer.model.Transaction;
import com.gft.algo.gamer.model.TransactionType;
import com.gft.algo.gamer.repository.AccountRepository;
import com.gft.algo.gamer.repository.AssetRepository;
import com.gft.algo.gamer.repository.PortfolioRepository;

import junit.framework.Assert;

/**
 * Created by iozi on 06/10/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})

public class ExampleTest {
	@Autowired
	NewTransactionAddingService newTransactionAdding;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	PortfolioRepository portfolioRepository;
	@Autowired
	DataDownloadService dataDownloadService;
	@Autowired
	AssetRepository assetRepo;
	@Before
	public void init() {

		Account account = accountRepository.findOne("Wosin");
		account.setBalance(new BigDecimal(10000.00));

	}
	@Test
	public void test() throws DataAccessException {
		Transaction transaction = new Transaction();

		transaction.setPrice(new BigDecimal(100));
		transaction.setTicker("MSFT");
		transaction.setType(TransactionType.BUY);
		transaction.setVolume(10);

		StockData stockData = dataDownloadService.downloadNewStock("MSFT");
		Account account = new Account("adam", "haslo");
		Portfolio portfolio = new Portfolio();
		List<Portfolio> ports = new ArrayList<>();
		portfolio.setName("Portfail");
		portfolio.setAccount(account);
		ports.add(portfolio);

		account.setPortfolioList(ports);
		accountRepository.save(account);
		accountRepository.flush();

		transaction.setPortfolio(portfolioRepository
				.GetPortfolioforNameAndLogin("adam", "Portfail"));

		newTransactionAdding.addNewTransaction(transaction, "adam", null);
		Account account1 = accountRepository.GetEagerPOrtfolio("adam");
		BigDecimal assumed = (new BigDecimal(10000.00).subtract(stockData
				.getPrice().multiply(new BigDecimal(transaction.getVolume()))));
		Assert.assertEquals(assumed.setScale(1, BigDecimal.ROUND_UP),
				account1.getBalance().setScale(1, BigDecimal.ROUND_UP));
		Assert.assertTrue(account1.getPortfolioList().get(0).getAssets().get(0)
				.getTicker().contains("MSFT"));
	}
	@Test
	public void testIfCanAfford() {
		Transaction transaction = new Transaction();
		Portfolio port = new Portfolio();
		port.setName("Portfolio");
		portfolioRepository.saveAndFlush(port);
		transaction.setPortfolio(port);
		transaction.setPrice(new BigDecimal(1200));
		transaction.setTicker("MSFT");
		transaction.setType(TransactionType.BUY);
		transaction.setVolume(100000000);

		Assert.assertEquals("Not Enough Money", newTransactionAdding
				.addNewTransaction(transaction, "Wosin", null));

	}
	@Test
	public void testIfSells() throws DataAccessException {
		Transaction transaction = new Transaction();

		transaction.setPrice(new BigDecimal(1200));
		transaction.setTicker("MSFT");
		transaction.setType(TransactionType.SELL);
		transaction.setVolume(10);

		StockData stockData = dataDownloadService.downloadNewStock("MSFT");
		Account account = accountRepository.GetEagerPOrtfolio("adam");
		transaction.setPortfolio(account.getPortfolioList().get(0));
		BigDecimal assumed = (account.getBalance().add(stockData.getPrice()
				.multiply(new BigDecimal(transaction.getVolume()))));
		Assert.assertTrue(account.getPortfolioList().get(0).getAssets().get(0)
				.getTicker().contains("MSFT"));
		newTransactionAdding.addNewTransaction(transaction, "adam",
				account.getPortfolioList().get(0).getAssets().get(0));

		Account account1 = accountRepository.GetEagerPOrtfolio("adam");
		
	Assert.assertTrue(account.getPortfolioList().get(0).getAssets().size()==0);
		Assert.assertEquals(assumed.floatValue(),
				account1.getBalance().floatValue());

	}
}