package com.gft.algo.gamer.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by iozi on 21/10/2015.
 */
@Entity
public class Portfolio {

    @Id
    private long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Asset> assets;

    public long getId() {
        return id;
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
