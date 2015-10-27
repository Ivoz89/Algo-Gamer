package com.gft.algo.gamer.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gft.algo.gamer.aspect.Log;
import com.gft.algo.gamer.model.Asset;

public class StockREST {
	 
	    @Log
	    @RequestMapping(value = "/stock/{login}/{ticker}/{volume}", method = RequestMethod.POST)
	    public Asset postNewStock(@PathVariable("ticker") String ticker,@PathVariable("login") String login,@PathVariable("volume") int volume) {

	    	return null;
	
	}
}
