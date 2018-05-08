package com.gk.assessment.gkassessment.web.controllers;

import com.gk.assessment.gkassessment.registry.UsersSessionRegistry;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by AYAZ on 16/04/2018.
 */
@Controller
public class LogoutController {

    private static final Logger LOG = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private UsersSessionRegistry userSessionRegistry;

    public static final String LOGIN_VIEW_NAME = "user/login?logout";

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        List<SessionInformation> activeSessions = userSessionRegistry.getActiveSessions();
        LOG.info("Before logout Active sessions list=" + activeSessions.size());
        if (request != null && request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null) {
            userSessionRegistry.removePreviousActiveSessionsForUser(request.getUserPrincipal().getName(), RequestContextHolder.currentRequestAttributes().getSessionId());
        }
        activeSessions = userSessionRegistry.getActiveSessions();
        LOG.info("After logout Active sessions list=" + activeSessions.size());
        return LOGIN_VIEW_NAME;
    }

}
