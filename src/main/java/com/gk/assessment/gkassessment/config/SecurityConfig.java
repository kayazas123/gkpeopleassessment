package com.gk.assessment.gkassessment.config;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
    private static final String[] PUBLIC_MATCHERS = {
	"/webjars/**",
	"/css/**",
	"/js/**",
	"/images/**",
	"/",
	"/users/**",
	"/error/**",
	"/console/**"
    };
    @Autowired
    private Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

	http.csrf().disable();
	http.headers().frameOptions().disable();

	http
	    .authorizeRequests()
	    .antMatchers(PUBLIC_MATCHERS).permitAll()
	    .anyRequest().authenticated()
	    .and()
	    .formLogin().loginPage("/login").defaultSuccessUrl("/users")
	    .failureUrl("/login?error").permitAll()
	    .and()
	    .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER");
    }


}
