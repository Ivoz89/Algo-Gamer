package com.gft.algo.gamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.algo.gamer.model.Portfolio;

/**
 * Created by iozi on 22/10/2015.
 */
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
	@Query("SELECT p from Portfolio p  LEFT JOIN FETCH p.assets WHERE p.account.login= (:login) AND p.name = (:name)")
	Portfolio GetPortfolioforNameAndLogin(@Param("login") String login,@Param("name") String name);
	@Query("SELECT p from Portfolio p  LEFT JOIN FETCH p.assets WHERE p.account.login= (:login) ")
	List<Portfolio> GetPortfoliosforUser(@Param("login") String login);
}
