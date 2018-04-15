package com.gk.assessment.gkassessment.web.controllers;

import com.gk.assessment.gkassessment.registry.UsersSessionRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Controller
public class LoginController {
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    public static final String LOGIN_VIEW_NAME = "user/login";

    @Autowired
    private UsersSessionRegistry userSessionRegistry;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
	return LOGIN_VIEW_NAME;
    }
}
