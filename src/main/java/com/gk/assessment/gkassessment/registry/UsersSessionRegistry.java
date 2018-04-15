package com.gk.assessment.gkassessment.registry;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
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
	for ( Object principal : sessionRegistry.getAllPrincipals() ){
	    List<SessionInformation> activeSessions =  sessionRegistry.getAllSessions( username, false);
	    for(SessionInformation sessionInformation:activeSessions){
		if(sessionInformation.getSessionId() != currentSessionID)
		    logoutSession(sessionInformation);
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
