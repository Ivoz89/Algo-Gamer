package com.gft.algo.gamer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gft.algo.gamer.config.Application;
import com.gft.algo.gamer.model.StockData;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})

public class StockDataDownloadTest {
@Autowired
DataDownloadService dataDownloadService;


@Test
public void testIfDataIsDownloaded() {
	
	try {
		StockData stockData = dataDownloadService.downloadNewStock("MSFT");
		Assert.assertEquals("MSFT", stockData.getTicker());
		Assert.assertEquals("Microsoft Corporation", stockData.getFullName());
		System.out.println(stockData.getPrice());
	} catch (DataAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
