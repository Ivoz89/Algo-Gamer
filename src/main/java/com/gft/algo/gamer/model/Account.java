package com.gft.algo.gamer.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by iozi on 21/10/2015.
 */
@Entity
public class Account {

    public Account(String login, String password) {
		super();
		this.login = login;
		this.password = password;
		this.balance= new BigDecimal(10000.00);
		this.portfolioList = new ArrayList<>();
	}

	public Account() {
		super();
	
	
	}

	@Id
    private String login;

    @Override
	public String toString() {
		return "Account [login=" + login + ", password=" + password
				+ ", portfolioList=" + portfolioList + ", balance=" + balance
				+ "]";
	}
    @JsonIgnore
	private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity=Portfolio.class)
    private List<Portfolio> portfolioList;

    private BigDecimal balance;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Portfolio> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
