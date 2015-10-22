package com.gft.algo.gamer.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.algo.gamer.aspect.Log;
import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.Transaction;
import com.gft.algo.gamer.repository.AccountRepository;
import com.gft.algo.gamer.repository.TransactionRepository;
@Service
public class NewTransactionAddingService {

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	TransactionRepository transactionRepository;
@Log
	public String addNewTransaction(Transaction transaction,String username)
	{
		 final Logger logger = LoggerFactory.getLogger(NewTransactionAddingService.class);
		Account account = accountRepository.findOne(username);
		BigDecimal price = transaction.getPrice().multiply(new BigDecimal(transaction.getVolume()));
		switch(transaction.getType())
		{
			case BUY:
				if(canAfford(account, price))
				{
					account.setBalance(account.getBalance().subtract(price));
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

				account.setBalance(account.getBalance().add(price));
				accountRepository.saveAndFlush(account);
				transactionRepository.saveAndFlush(transaction);
				logger.info("Sucesfull Sell ");
				return "Assests Sold Sucesfully";
				
			
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
