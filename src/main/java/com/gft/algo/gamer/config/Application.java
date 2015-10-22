/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gft.algo.gamer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.gft.algo.gamer.model.Account;
import com.gft.algo.gamer.repository.AccountRepository;

@Configuration
@EnableScheduling
@EnableAutoConfiguration
@ComponentScan("com.gft")
@SpringBootApplication
@EnableJpaRepositories("com.gft")
@EnableTransactionManagement
@EntityScan("com.gft")
public class Application extends SpringBootServletInitializer {

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	 @Bean
	    CommandLineRunner init(final AccountRepository accountRepository) {
	      
	      return new CommandLineRunner() {
	 
	        @Override
	        public void run(String... arg0) throws Exception {
	        	PasswordEncoder encoder = new BCryptPasswordEncoder();
	        	
	          accountRepository.save(new Account("Wosin", encoder.encode("password")));
	          
	        }
	        
	      };
}
}
@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
 
  @Autowired
  AccountRepository accountRepository;
 
  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
	  auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }
 
  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {
 
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findOne(username);
        if(account != null) {
        return new User(account.getLogin(), account.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + username + "'");
        }
      }
      
    };

  }
  @Bean
 	public PasswordEncoder passwordEncoder(){
 		PasswordEncoder encoder = new BCryptPasswordEncoder();
 		return encoder;
 	}
  }

 
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().fullyAuthenticated().and().
    httpBasic().and().
    csrf().disable();
  }
 
  @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
  @Bean
 	public PasswordEncoder passwordEncoder(){
 		PasswordEncoder encoder = new BCryptPasswordEncoder();
 		return encoder;
 	}
}
