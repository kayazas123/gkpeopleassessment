package com.gk.assessment.gkassessment.web.controllers.advices;

import com.gk.assessment.gkassessment.registry.UsersSessionRegistry;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Controller
public class UsersControler {
    public static final String USER_VIEW_NAME = "user/users";

    private static final Logger LOG = LoggerFactory.getLogger(UsersControler.class);

    @Autowired
    private UsersSessionRegistry userSessionRegistry;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getUserUrl(Locale locale, Model model){
	List<SessionInformation> activeSessions = userSessionRegistry.getActiveSessions();
	model.addAttribute("activeSessions", activeSessions);
	return USER_VIEW_NAME;
    }
}
