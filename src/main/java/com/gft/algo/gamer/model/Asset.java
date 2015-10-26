package com.gft.algo.gamer.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by iozi on 21/10/2015.
 */
@Entity	
public class Asset {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    public Asset() {
		super();
	}

	@ManyToOne
    private Portfolio portfolio;

    private String ticker;

    private int volume;

    private BigDecimal priceBought;

    public long getId() {
        return id;
    }

    public Asset(StockData stockData,int volume,Portfolio portfolio) {
		super();
		
		this.portfolio=portfolio;
		this.ticker = stockData.getTicker();
		this.volume = volume;
		this.priceBought = stockData.getPrice();
	}

	public void setId(long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public BigDecimal getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(BigDecimal priceBought) {
        this.priceBought = priceBought;
    }
}
