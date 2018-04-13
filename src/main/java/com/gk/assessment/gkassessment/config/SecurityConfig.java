package com.gk.assessment.gkassessment.config;

import com.gk.assessment.gkassessment.backend.service.JwtAuthenticationEntryPoint;
import com.gk.assessment.gkassessment.backend.service.JwtAuthorizationTokenFilter;
import com.gk.assessment.gkassessment.backend.service.JwtTokenUtil;
import com.gk.assessment.gkassessment.backend.service.JwtUserDetailsService;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
	"/users/**",
	"/error/**",
	"/console/**"
    };
    @Autowired
    private Environment env;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    private static final String SALT = "fdalkjalk;3jlwf00sfaof";


    @Override
    protected void configure(HttpSecurity http) throws Exception {
	List<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

	// Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
	http.csrf().disable()
	    .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	    .authorizeRequests()
	    .antMatchers(PUBLIC_MATCHERS).permitAll()
	    .anyRequest().authenticated().and()
	    .formLogin().loginPage("/login").defaultSuccessUrl("/users")
	    .failureUrl("/login?error").permitAll().and()
	    .logout().permitAll();
	JwtAuthorizationTokenFilter authenticationTokenFilter = new JwtAuthorizationTokenFilter(userDetailsService(), jwtTokenUtil, tokenHeader);
	http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

	// disable page caching
	http
	    .headers()
	    .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
	    .cacheControl();

/*
	http
	    .authorizeRequests()
	    .antMatchers(PUBLIC_MATCHERS).permitAll()
	    .anyRequest().authenticated()
	    .and()
	    .formLogin().loginPage("/login").defaultSuccessUrl("/users")
	    .failureUrl("/login?error").permitAll()
	    .and()
	    .logout().permitAll();
*/
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth
	    .userDetailsService(jwtUserDetailsService)
	    .passwordEncoder(passwordEncoder());

    }
}
