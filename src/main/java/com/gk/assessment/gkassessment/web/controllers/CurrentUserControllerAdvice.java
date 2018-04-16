package com.gk.assessment.gkassessment.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by AYAZ on 16/04/2018.
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public String getCurrentUser(Authentication authentication) {
	String username = "";
	UserDetails userDetails = (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
	if(userDetails != null && userDetails.getUsername() != null){
	    username =  userDetails.getUsername();
	}
	return username;
    }
}
