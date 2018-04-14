package com.gk.assessment.gkassessment.web.controllers;

import com.gk.assessment.gkassessment.web.domain.frontend.BasicAccountPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AYAZ on 14/04/2018.
 */
@Controller
public class SignupController {

    private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);

    public static final String SIGNUP_URL_MAPPING = "/signup";
    public static final String SUBSCRIPION_VIEW_NAME = "registration/signup";
    public static final String MODEL_KEY_NAME = "payload";public static final String DUPLICATED_USERNAME_KEY = "duplicatedUsername";
    public static final String DUPLICATED_EMAIL_KEY = "duplicatedEmail";
    public static final String SIGNED_UP_MESSAGE_KEY = "signedUp";
    public static final String ERROR_MESSAGE_KEY = "message";
    public static final String GENERIC_ERROR_VIEW_NAME = "error/genericError";

    @RequestMapping(value = SIGNUP_URL_MAPPING)
    public String signupGet(ModelMap model){
	LOG.info("Redirecting to "+SUBSCRIPION_VIEW_NAME);
	model.addAttribute(MODEL_KEY_NAME,new BasicAccountPayload());
	return SUBSCRIPION_VIEW_NAME;
    }
}
