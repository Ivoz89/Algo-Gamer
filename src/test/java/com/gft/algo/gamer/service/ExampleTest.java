package com.gft.algo.gamer.service;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gft.algo.gamer.config.Application;
import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.model.Transaction;
import com.gft.algo.gamer.model.TransactionType;
import com.gft.algo.gamer.repository.AccountRepository;
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
@Before
public void  init(){
	
	Account account = accountRepository.findOne("Wosin");
	account.setBalance(new BigDecimal(10000));
	accountRepository.save(account);
	accountRepository.flush();
	
	
}
    @Test
    public void test() {
    	Transaction transaction = new Transaction();

   transaction.setPrice(new BigDecimal(100));
   transaction.setTicker("MSFT");
   transaction.setType(TransactionType.BUY);
   transaction.setVolume(10);

       newTransactionAdding.addNewTransaction(transaction, "Wosin");
       Account account = accountRepository.findOne("Wosin");
       BigDecimal assumed = new BigDecimal(10000).subtract(transaction.getPrice().multiply(new BigDecimal(transaction.getVolume())));
     
       Assert.assertEquals(assumed.doubleValue(), account.getBalance().doubleValue() );
      
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
   transaction.setVolume(10);

       Assert.assertEquals("Not Enough Money", newTransactionAdding.addNewTransaction(transaction, "Wosin"));
       
    }
    @Test
    public void testIfSells() {
    	Transaction transaction = new Transaction();
   

   transaction.setPrice(new BigDecimal(1200));
   transaction.setTicker("MSFT");
   transaction.setType(TransactionType.SELL);
   transaction.setVolume(10);
   BigDecimal assumed = new BigDecimal(10000).add(transaction.getPrice().multiply(new BigDecimal(transaction.getVolume())));
   newTransactionAdding.addNewTransaction(transaction, "Wosin");
   Account account = accountRepository.findOne("Wosin");

   Assert.assertEquals(assumed.doubleValue(), account.getBalance().doubleValue() );
       
    }
}