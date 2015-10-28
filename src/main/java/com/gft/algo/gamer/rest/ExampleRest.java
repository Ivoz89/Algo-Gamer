package com.gft.algo.gamer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.repository.AccountRepository;

/**
 * Created by iozi on 06/10/2015.
 */
@RestController
public class ExampleRest {
@Autowired
AccountRepository accountRepo;
    @RequestMapping(value = "/user/reg", method = RequestMethod.POST)
    public Account postNewAsset(@RequestBody Account user) {
       Account account = new Account(user.getLogin(),user.getPassword());
       accountRepo.saveAndFlush(account);
       return account;
    }

}
