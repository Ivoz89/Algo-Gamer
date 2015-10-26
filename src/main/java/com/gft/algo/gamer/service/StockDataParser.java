package com.gft.algo.gamer.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.gft.algo.gamer.aspect.LogNoArgs;
import com.gft.algo.gamer.model.StockData;
import com.jayway.restassured.path.json.JsonPath;
@Service
public class StockDataParser {
    @LogNoArgs
    public StockData fromJson(String json) {
        JsonPath jsonPath = new JsonPath(json);
        StockData stockData = new StockData();
        stockData.setTicker(jsonPath.getString("list.resources[0].resource.fields.symbol"));
        stockData.setFullName(jsonPath.getString("list.resources[0].resource.fields.name"));
        stockData.setPrice(new BigDecimal(jsonPath.getDouble("list.resources[0].resource.fields.price")));
        return stockData;
    }
}
