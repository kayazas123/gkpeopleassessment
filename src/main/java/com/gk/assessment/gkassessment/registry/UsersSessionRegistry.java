package com.gk.assessment.gkassessment.registry;

import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * Created by AYAZ on 15/04/2018.
 */
@Component
public class UsersSessionRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(UsersSessionRegistry.class);

    @Autowired
    private SessionRegistry sessionRegistry;


    public List<SessionInformation> getActiveSessions()
    {
	List<SessionInformation> activeSessions = new ArrayList<>();
	for ( Object principal : sessionRegistry.getAllPrincipals() ){
	    activeSessions.addAll( sessionRegistry.getAllSessions( principal, false ) );
	}
	return activeSessions;
    }

    public void removePreviousActiveSessionsForUser(String username,String currentSessionID){
	LOG.info("Find and retrive user={} sessionid={} sizeofPrincipals={}",username,currentSessionID,sessionRegistry.getAllPrincipals());
	for (Object principal : sessionRegistry.getAllPrincipals()) {
	    if (principal instanceof User) {
		UserDetails userDetails = (UserDetails) principal;
		LOG.info("UserDetails username={}",userDetails.getUsername());
		if (userDetails.getUsername().equals(username)) {
		    LOG.info("Retrieved session for user={}",username);
		    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
			if(currentSessionID != information.getSessionId()) {
			    LOG.info("Found session for username={} oldsession={} newsession={}", username, information.getSessionId(), currentSessionID);
			    information.expireNow();
			}
		    }
		}
	    }
	}
    }


    public User getUser( SessionInformation session )
    {
	Object principalObj = session.getPrincipal();
	if ( principalObj instanceof User )
	{
	    User user = (User) principalObj;
	    return user;
	}
	return null;
    }


    public void logoutSession(SessionInformation sessionInformation) {
	if (sessionInformation != null) {
	    LOG.info("Removing session from list for user={} and sessionid={}",sessionInformation,sessionInformation.getPrincipal());
	    sessionInformation.expireNow();
	}
    }


}
