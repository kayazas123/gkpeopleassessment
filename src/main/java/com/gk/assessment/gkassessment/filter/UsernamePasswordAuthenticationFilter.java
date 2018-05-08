package com.gk.assessment.gkassessment.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class UsernamePasswordAuthenticationFilter {

    @Autowired
    AuthenticationSuccessHandler successHandler;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthenticationFailureHandler failureHandler;

    public void login(HttpServletRequest request, HttpServletResponse response, String username, String password) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));//if request is needed during authentication
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            //if failureHandler exists
            try {
                failureHandler.onAuthenticationFailure(request, response, e);
            } catch (IOException | ServletException se) {
                //ignore
            }
            throw e;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        successHandler.onAuthenticationSuccess(request, response, auth);//if successHandler exists
        //if user has a http session you need to save context in session for subsequent requests
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
}
