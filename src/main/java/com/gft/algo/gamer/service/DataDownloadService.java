package com.gft.algo.gamer.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gft.algo.gamer.model.StockData;
@Service
public class DataDownloadService {
	 final Logger logger = LoggerFactory.getLogger(NewTransactionAddingService.class);
	   @Value("${data.current.url.prefix}")
	    private String currentDataUrlPrefix;
	   private StringBuilder urlBuilder = new StringBuilder();
	    @Value("${data.current.url.suffix}")
	    private String currentDataUrlSuffix;
@Autowired
StockDataParser stockDataParser;
    public StockData downloadNewStock(String ticker) throws DataAccessException {
        try {
            StockData stockData = stockDataParser.fromJson(loadStockInfo(currentDataUrlPrefix, currentDataUrlSuffix, ticker));
            return stockData;
        } catch (IOException ex) {
            logger.error("Failed to obtain new stock data", ex);
            throw new DataAccessException(ex);
        }
    }

    private String loadStockInfo(String prefix, String suffix, String ticker) throws IOException {
        urlBuilder.setLength(0);
        URL stockDataUrl = new URL(urlBuilder.append(prefix).append(ticker).append(suffix).toString());
        try(InputStream contentStream = stockDataUrl.openStream()) {
            return IOUtils.toString(contentStream);
        }
    }
}
