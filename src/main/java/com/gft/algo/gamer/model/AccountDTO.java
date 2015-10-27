package com.gft.algo.gamer.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class AccountDTO {
	@Id
	private String login;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,targetEntity=Portfolio.class)
	  private List<Portfolio> portfolioList;
	  private BigDecimal balance;
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public AccountDTO(String login, Collection<Portfolio> portfolioList,
			BigDecimal balance) {
		super();
		this.login = login;
		this.portfolioList = new ArrayList<Portfolio>(portfolioList);
		this.balance = balance;
	}
	public AccountDTO(String login, BigDecimal balance) {
		super();
		this.login = login;
		this.balance = balance;
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
	public AccountDTO() {
		super();
	}
}
