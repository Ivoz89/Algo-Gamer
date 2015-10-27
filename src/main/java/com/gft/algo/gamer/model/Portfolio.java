package com.gft.algo.gamer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by iozi on 21/10/2015.
 */
@Entity
public class Portfolio {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	@ManyToOne(targetEntity=Account.class)
    
 private Account account;




	@Override
public String toString() {
	return "Portfolio [id=" + id + ", account=" + account + ", name=" + name
			+ ", assets=" + assets + "]";
}

	public Account getAccount() {
	return account;
}

public void setAccount(Account account) {
	this.account = account;
}

	private String name;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity=Asset.class)
  
    private List<Asset> assets;

    public long getId() {
        return id;
    }

    public Portfolio() {
		super();
	this.assets = new ArrayList<>();
	}

	public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
