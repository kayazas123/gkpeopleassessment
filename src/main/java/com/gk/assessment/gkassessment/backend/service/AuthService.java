package com.gk.assessment.gkassessment.backend.service;


import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by AYAZ on 14/04/2018.
 */
@Service
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    public void Authenticate(User user){
	LOG.info("Logging in user {}",user.getUsername());
	Authentication auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(auth);

    }

}
