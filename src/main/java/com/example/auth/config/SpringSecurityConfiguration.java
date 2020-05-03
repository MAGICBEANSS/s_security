package com.example.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import  com.example.auth.dto.*;

@EnableWebSecurity
@Slf4j
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   // super.configure(auth);
    auth.inMemoryAuthentication()
        .withUser("MANAGER").password("MANAGER").roles(AuthoritiesConstants.MANAGER_ROLE)
        .and()
        .withUser("ADMIN").password("ADMIN").roles(AuthoritiesConstants.ADMIN_ROLE)
        .and()
        .withUser("MAINTAINER").password("MAINTAINER").roles(AuthoritiesConstants.MAINTAINER_ROLE)
        .and()
        .withUser("USER1").password("USER1").roles(AuthoritiesConstants.CUSTOMER_ROLE)
        .and()
        .withUser("USER2").password("USER2").roles(AuthoritiesConstants.CUSTOMER_ROLE);

  }

  @Bean
  PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) {
    try {

      httpSecurity.authorizeRequests()
         // .antMatchers("/admin/**","/private/**").hasAuthority(AuthoritiesConstants.ADMIN_ROLE)
          .antMatchers("/admin/**","/private/**","/admin","**/admin","**/admin/**").hasRole(AuthoritiesConstants.ADMIN_ROLE)
          .antMatchers("/management/**").hasAnyRole(AuthoritiesConstants.ADMIN_ROLE,AuthoritiesConstants.MAINTAINER_ROLE,AuthoritiesConstants.MANAGER_ROLE)
          .antMatchers("/home/**","/user/**").hasAnyRole(AuthoritiesConstants.ADMIN_ROLE,AuthoritiesConstants.MAINTAINER_ROLE,AuthoritiesConstants.MANAGER_ROLE,AuthoritiesConstants.CUSTOMER_ROLE)
      .and().formLogin();
    } catch (Exception e) {
      log.error("Unexpected Exception Occured {}",e);
    }
  }
}
