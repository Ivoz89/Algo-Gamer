package com.gft.algo.gamer.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.algo.gamer.model.Asset;
import com.gft.algo.gamer.model.Portfolio;
import com.gft.algo.gamer.repository.PortfolioRepository;

@Service
public class SellAssetService {
	@Autowired
	PortfolioRepository portfolioRepo;
	
	 final Logger logger = LoggerFactory.getLogger(SellAssetService.class);
	public Portfolio SellAsset (Asset asset, int volume,Portfolio portfolio) throws Exception{
List<Asset> assets = portfolio.getAssets();
logger.info(assets.toString());
	
		logger.info(assets.toString());

		if (asset.getVolume()-volume < 0)
		{
			logger.info("Not Enough Assets to sell");
			return null;
		}
		if(asset.getVolume()-volume >= 0 )
		{		assets.remove(asset);
			asset.setVolume(asset.getVolume()-volume);
			if(asset.getVolume()>0){
		assets.add(asset);
			}
		}
		
	
		portfolio.setAssets(assets);
		
		portfolioRepo.saveAndFlush(portfolio);
		
		
		return portfolio;
		
	}
}
