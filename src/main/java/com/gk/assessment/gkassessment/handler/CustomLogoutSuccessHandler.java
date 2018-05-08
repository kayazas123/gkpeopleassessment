package com.gk.assessment.gkassessment.handler;

import com.gk.assessment.gkassessment.registry.UsersSessionRegistry;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger LOG = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Autowired
    private UsersSessionRegistry userSessionRegistry;

    public CustomLogoutSuccessHandler() {
        super();
    }

    // API

    @Override
    public void onLogoutSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException, ServletException {
        List<SessionInformation> activeSessions = userSessionRegistry.getActiveSessions();
        SecurityContextHolder.getContext().setAuthentication(null);
        LOG.debug("Before logout Active sessions list=" + activeSessions.size());
        userSessionRegistry.removeCurrentActiveSessionsForUser(authentication.getName());

        activeSessions = userSessionRegistry.getActiveSessions();
        LOG.debug("After logout Active sessions list=" + activeSessions.size());
        request.getSession().invalidate();
        super.onLogoutSuccess(request, response, authentication);
    }

}
