package com.gft.algo.gamer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.model.AccountDTO;
import com.gft.algo.gamer.model.Portfolio;

/**
 * Created by iozi on 22/10/2015.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("SELECT new com.gft.algo.gamer.model.AccountDTO(s.login,s.balance ) FROM Account s WHERE s.login= (:login)")
	AccountDTO GetDTOforLogin(@Param("login") String login);

	@Query("SELECT s.portfolioList FROM Account s WHERE s.login= (:login)")
	List<Portfolio> GetPortfolios(@Param("login") String login);
	@Query("SELECT s FROM Account s LEFT JOIN FETCH  s.portfolioList al WHERE s.login= (:login)")
	Account GetEagerPOrtfolio(@Param("login") String login);
	
	
}
