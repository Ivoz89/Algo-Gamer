package com.gft.algo.gamer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.algo.gamer.aspect.Log;
import com.gft.algo.gamer.model.Asset;
import com.gft.algo.gamer.model.Transaction;
import com.gft.algo.gamer.model.TransactionDTO;
import com.gft.algo.gamer.repository.AssetRepository;
import com.gft.algo.gamer.repository.PortfolioRepository;
import com.gft.algo.gamer.service.DataAccessException;
import com.gft.algo.gamer.service.DataDownloadService;
import com.gft.algo.gamer.service.NewTransactionAddingService;
@RestController
public class AssetsRest {
	@Autowired
NewTransactionAddingService newTransactionService;
	@Autowired 
	PortfolioRepository portfolioRepo;
	@Autowired
	DataDownloadService dataDownloadService;
	@Autowired
	AssetRepository assetRepo;
    @Log
    @RequestMapping(value = "/assets/{login}", method = RequestMethod.POST)
    public String postNewAsset(@PathVariable("login") String login,@RequestBody TransactionDTO transactionDTO) throws DataAccessException {
    	Transaction transaction = new Transaction();
    	transaction.setPortfolio(portfolioRepo.GetPortfolioforNameAndLogin(login, transactionDTO.getPortfolio()));

transaction.setDate(transactionDTO.getDate());
transaction.setPrice(transactionDTO.getPrice());
transaction.setTicker(transactionDTO.getTicker());
transaction.setType(transactionDTO.getType());
transaction.setVolume(transactionDTO.getVolume());
newTransactionService.addNewTransaction(transaction, login, null);
    return "Sucesfully Bought";
}
    @RequestMapping(value = "/assets/{login}", method = RequestMethod.GET)
    public List<Asset> postNewAsset(@PathVariable("login") String login) throws DataAccessException {

    return assetRepo.GetEagerAll(login);
}
    @RequestMapping(value = "/assets/{login}/sell", method = RequestMethod.POST)
    public Asset SellAssets(@PathVariable("login") String login,@RequestBody TransactionDTO transactionDTO) throws DataAccessException {
    	Transaction transaction = new Transaction();
    	transaction.setPortfolio(portfolioRepo.GetPortfolioforNameAndLogin(login, transactionDTO.getPortfolio()));

transaction.setDate(transactionDTO.getDate());
transaction.setPrice(transactionDTO.getPrice());
transaction.setTicker(transactionDTO.getTicker());
transaction.setType(transactionDTO.getType());
transaction.setVolume(transactionDTO.getVolume());
newTransactionService.addNewTransaction(transaction, login, assetRepo.findOne(transactionDTO.getAssetID()));
     assetRepo.delete(transactionDTO.getAssetID());
     return assetRepo.findOne(transactionDTO.getAssetID());
}
}
