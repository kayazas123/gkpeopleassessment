package com.gk.assessment.gkassessment.backend.service;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * Created by AYAZ on 14/04/2018.
 */
@Service
public class AuthService implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        LOG.debug("Authenticating {}", authentication);
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        return new UsernamePasswordAuthenticationToken(
                name, password, new ArrayList<>());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}
