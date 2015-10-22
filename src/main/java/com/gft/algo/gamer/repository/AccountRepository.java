package com.gft.algo.gamer.repository;

import com.gft.algo.gamer.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by iozi on 22/10/2015.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
