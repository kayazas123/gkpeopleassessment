package com.gk.assessment.gkassessment.backend.service;

import com.gk.assessment.gkassessment.backend.persistence.repositories.UserRepository;
import com.gk.assessment.gkassessment.web.domain.frontend.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by AYAZ on 13/04/2018.
 */
@Component
public class JwtUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userRepository.findByUsername(username);

	if (user == null) {
	    throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
	} else {
	    return JwtUserFactory.create(user);
	}
    }

}
