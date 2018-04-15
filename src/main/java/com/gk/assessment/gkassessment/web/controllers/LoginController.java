package com.gk.assessment.gkassessment.web.controllers;

import com.gk.assessment.gkassessment.registry.UsersSessionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    public static final String LOGIN_VIEW_NAME = "user/login";

    @Autowired
    private UsersSessionRegistry userSessionRegistry;

    @RequestMapping("/login")
    public String login(){
	LOG.info("Redirecting to {}",LOGIN_VIEW_NAME);
	return LOGIN_VIEW_NAME;
    }
}
