package com.gk.assessment.gkassessment.backend.service;


import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by AYAZ on 14/04/2018.
 */
@Service
public class AuthService {

    public void Authenticate(User user){
	Authentication auth = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(auth);
    }

}
