package com.gft.algo.gamer.model;

import java.math.BigDecimal;

public class StockData {
    private String ticker;

    public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	private String fullName;
    private BigDecimal price;
    
}
