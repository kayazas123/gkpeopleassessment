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
	for (Object principal : sessionRegistry.getAllPrincipals()) {
	    if (principal instanceof User) {
		UserDetails userDetails = (UserDetails) principal;
		if (userDetails.getUsername().equals(username)) {
		    for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, true)) {
			if(currentSessionID != information.getSessionId()) {
			    information.expireNow();
			}
		    }
		}
	    }
	}
    }
}
