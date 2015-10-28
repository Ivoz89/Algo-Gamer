package com.gft.algo.gamer.model;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {
private String portfolio;
private TransactionType type;
private String ticker;
private int volume;
private BigDecimal price;
public long getAssetID() {
	return assetID;
}
public void setAssetID(int assetID) {
	this.assetID = assetID;
}
private Date date;
private int assetID;
public String getPortfolio() {
	return portfolio;
}
public void setPortfolio(String portfolio) {
	this.portfolio = portfolio;
}
public TransactionType getType() {
	return type;
}
public void setType(TransactionType type) {
	this.type = type;
}
public String getTicker() {
	return ticker;
}
public void setTicker(String ticker) {
	this.ticker = ticker;
}
public int getVolume() {
	return volume;
}
public void setVolume(int volume) {
	this.volume = volume;
}
public BigDecimal getPrice() {
	return price;
}
public void setPrice(BigDecimal price) {
	this.price = price;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}

}
