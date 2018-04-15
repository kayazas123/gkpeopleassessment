package com.gk.assessment.gkassessment.web.controllers;

import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.UserRole;
import com.gk.assessment.gkassessment.backend.service.AuthService;
import com.gk.assessment.gkassessment.backend.service.UserService;
import com.gk.assessment.gkassessment.utils.UserUtils;
import com.gk.assessment.gkassessment.web.domain.frontend.BasicAccountPayload;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AYAZ on 14/04/2018.
 */
@Controller
public class SignupController {

    private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    public static final String SIGNUP_URL_MAPPING = "/signup";
    public static final String SUBSCRIPION_VIEW_NAME = "registration/signup";
    public static final String MODEL_KEY_NAME = "payload";
    public static final String DUPLICATED_USERNAME_KEY = "duplicatedUsername";
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

    @RequestMapping(value = SIGNUP_URL_MAPPING, method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute(MODEL_KEY_NAME) @Valid BasicAccountPayload payload,
				ModelMap model) throws IOException {
	LOG.info("Recieved from form="+payload);
	List<String> errorMessages = new ArrayList<>();
	this.checkForDuplicates(payload,model);
	boolean validationFailed = false;

	if(!payload.getPassword().equals(payload.getConfirmPassword())){
	    LOG.warn("Password and Confirm password is not same");
	    model.addAttribute(SIGNED_UP_MESSAGE_KEY, "false");
	    errorMessages.add("Password and Conform Password should be same");
	    validationFailed = true;
	}

	if(duplicateCheck(model,errorMessages)){
	    validationFailed = true;

	}

	if(validationFailed){
	    model.addAttribute(SIGNED_UP_MESSAGE_KEY,"false");
	    return SUBSCRIPION_VIEW_NAME;
	}

	LOG.info("Transforming user payload into User domain object");
	User user = UserUtils.fromWebUserToDomainUser(payload);
	Set<UserRole> userRoleSet = new HashSet<>();
	userRoleSet.add(new UserRole(user,UserUtils.createBasicRole(RolesEnum.BASIC)));
	User registeredUser = userService.createUser(user,userRoleSet);

	//Autologin registered users
	authService.Authenticate(registeredUser);

	LOG.info("User Created successfully {}",registeredUser);
	model.addAttribute(SIGNED_UP_MESSAGE_KEY,"true");


	return SUBSCRIPION_VIEW_NAME;
    }

    private boolean duplicateCheck(ModelMap model,List<String> errorMessages){
	boolean duplicates = false;
	if (model.containsKey(DUPLICATED_USERNAME_KEY)) {
	    LOG.warn("The username already exists. Displaying error to the user");
	    model.addAttribute(SIGNED_UP_MESSAGE_KEY, "false");
	    errorMessages.add("Username already exist");
	    duplicates = true;
	}

	if (model.containsKey(DUPLICATED_EMAIL_KEY)) {
	    LOG.warn("The email already exists. Displaying error to the user");
	    model.addAttribute(SIGNED_UP_MESSAGE_KEY, "false");
	    errorMessages.add("Email already exist");
	    duplicates = true;
	}

	return duplicates;
    }

    private void checkForDuplicates(BasicAccountPayload payload, ModelMap model) {

	// Username
	if (userService.findByUserName(payload.getUsername()) != null) {
	    model.addAttribute(DUPLICATED_USERNAME_KEY, true);
	}
	if (userService.findByUserEmail(payload.getEmail()) != null) {
	    model.addAttribute(DUPLICATED_EMAIL_KEY, true);
	}

    }
}
