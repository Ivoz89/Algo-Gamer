package com.gft.algo.gamer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.algo.gamer.model.Asset;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.model.StockData;
import com.gft.algo.gamer.repository.PortfolioRepository;
@Service
public class NewAssetService {
	@Autowired
	PortfolioRepository portfolioRepo;
	
public Portfolio BuyNewAsset (StockData stockData, int volume,Portfolio potrfolio){

	Asset asset = new Asset();
	asset.setPortfolio(potrfolio);
	asset.setPriceBought(stockData.getPrice());
	asset.setTicker(stockData.getTicker());
	asset.setVolume(volume);
	
	List<Asset> assets =potrfolio.getAssets();
	assets.add(asset);
	
	potrfolio.setAssets(assets);
	
	portfolioRepo.saveAndFlush(potrfolio);
	
	
	return potrfolio;
	
}
}
