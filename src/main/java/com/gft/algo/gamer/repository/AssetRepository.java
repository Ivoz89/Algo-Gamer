package com.gft.algo.gamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.algo.gamer.model.Asset;

/**
 * Created by iozi on 22/10/2015.
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
	@Query("SELECT  s FROM Asset s  inner join s.portfolio p inner join p.account a WHERE s.portfolio.account.login= (:login)")
	List<Asset> GetEagerAll(@Param("login") String login);
}
