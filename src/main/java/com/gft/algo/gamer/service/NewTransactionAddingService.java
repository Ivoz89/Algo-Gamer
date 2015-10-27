package com.gft.algo.gamer.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.algo.gamer.aspect.Log;
import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.model.StockData;
import com.gft.algo.gamer.model.Transaction;
import com.gft.algo.gamer.repository.AccountRepository;
import com.gft.algo.gamer.repository.TransactionRepository;
@Service
public class NewTransactionAddingService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	DataDownloadService dataDownloadService;
	@Autowired
	NewAssetService newAssetService;
@Log
	public String addNewTransaction(Transaction transaction,String username)
	{
		 final Logger logger = LoggerFactory.getLogger(NewTransactionAddingService.class);
		Account account = accountRepository.GetEagerPOrtfolio(username);
		StockData stockData = null;
BigDecimal price =null;
List<Portfolio> portfolios = account.getPortfolioList();
		
		try {
		 stockData = dataDownloadService.downloadNewStock(transaction.getTicker());
			
		 price = stockData.getPrice().multiply(new BigDecimal(transaction.getVolume()));
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int compare = stockData.getPrice().compareTo(transaction.getPrice()) ;
		switch(transaction.getType())
		{
			case BUY:
				if(canAfford(account, price))
				{
					if(compare == -1 || compare == 0  )
					account.setBalance(account.getBalance().subtract(price));
				newAssetService.BuyNewAsset(stockData, transaction.getVolume(),transaction.getPortfolio());
			
					accountRepository.saveAndFlush(account);
					transactionRepository.saveAndFlush(transaction);
					
					logger.info("Asset Bought Sucesfully");
					return "Assests Bought Sucesfully";
			
					
				}
				else {
					logger.info("Not Enough Money");
					return "Not Enough Money";
				}
				
			case SELL:
if(compare ==0 || compare == -1 ){
				account.setBalance(account.getBalance().add(price));
				accountRepository.saveAndFlush(account);
				transactionRepository.saveAndFlush(transaction);
				logger.info("Sucesfull Sell ");
				return "Assests Sold Sucesfully";
				
}
		}
		return null;
	
	}
	public boolean canAfford(Account account,BigDecimal price)
	{
		if(account.getBalance().compareTo(price) >0 )
			return true;
		else 
			return false;
		
		
		
	}
}
