package com.gft.algo.gamer.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by iozi on 21/10/2015.
 */
@Entity
public class Account {

    public Account(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
    private String login;

    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
