package com.gk.assessment.gkassessment.config;

import com.gk.assessment.gkassessment.backend.service.UserSecurityService;
import com.gk.assessment.gkassessment.handler.CustomLogoutSuccessHandler;
import com.gk.assessment.gkassessment.web.controllers.SignupController;
import java.security.SecureRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);
    private static final String[] PUBLIC_MATCHERS = {
	"/webjars/**",
	"/css/**",
	"/js/**",
	"/images/**",
	"/",
	"/error/**",
	"/contact/**",
	"/api/**",
	"/api/users/**",
	"/api/users/user",
	"/api/user/login",
	"/api/user/add",
	"/api/user/logout/*",
	"/console/**",
	SignupController.SIGNUP_URL_MAPPING
    };

    @Autowired
    private UserSecurityService userSecurityService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    /**
     * The encryption SALT. In real time we need to hide this
     */
    private static final String SALT = "fdalkjalk;3jlwf00sfaof";


    @Override
    protected void configure(HttpSecurity http) throws Exception {

	LOG.info("Login as Service");
	http.csrf().disable();
	http.headers().frameOptions().disable();

	http
	    .authorizeRequests()
	    .antMatchers(PUBLIC_MATCHERS).permitAll()
	    .anyRequest().authenticated().and()
	    .formLogin().loginPage("/login").defaultSuccessUrl("/users")
	    .failureUrl("/login?error").permitAll().and()
	    .logout()
	    .deleteCookies()
	    .clearAuthentication(true)
	    .invalidateHttpSession(true)
	    .logoutSuccessHandler(logoutSuccessHandler())
	    .and().sessionManagement().maximumSessions(1).expiredUrl("/login")
	    .maxSessionsPreventsLogin(false).sessionRegistry(sessionRegistry());

	// disable page caching
	http
	    .headers()
	    .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
	    .cacheControl();
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
	return new CustomLogoutSuccessHandler();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
	return new SessionRegistryImpl();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }


}
