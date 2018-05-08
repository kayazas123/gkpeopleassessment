package com.gk.assessment.gkassessment.registry;

import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * @return
     */
    public List<SessionInformation> getActiveSessions() {
        List<SessionInformation> activeSessions = new ArrayList<>();
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            activeSessions.addAll(sessionRegistry.getAllSessions(principal, false));
        }
        return activeSessions;
    }

    /**
     * @param username
     * @param currentSessionID
     */
    public void removePreviousActiveSessionsForUser(String username, String currentSessionID) {
        List<SessionInformation> sessionInformationList = getSessionInformationForUser(username);
        for (SessionInformation information : sessionInformationList) {
            if (currentSessionID != information.getSessionId()) {
                information.expireNow();
            }
        }
    }

    /**
     * @param username
     * @return
     */
    private List<SessionInformation> getSessionInformationForUser(String username) {
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof User) {
                UserDetails userDetails = (UserDetails) principal;
                if (userDetails.getUsername().equals(username)) {
                    LOG.debug("SizeOf current sessions in sessionRegistry=" + sessionRegistry.getAllSessions(userDetails, false).size());
                    return sessionRegistry.getAllSessions(userDetails, false);
                }
            }
        }
        return Collections.emptyList();
    }


    /**
     * @param username
     */
    public void removeCurrentActiveSessionsForUser(String username) {
        LOG.debug("Removing current session from sessionregistry for currentSessionID={}", username);
        List<SessionInformation> sessionInformationList = getSessionInformationForUser(username);
        if (sessionInformationList.size() == 1) {
            LOG.debug("User had only one session ..");
            sessionInformationList.get(0).expireNow();
        } else if (sessionInformationList.size() > 1) {
            LOG.debug("Morethan 1 session found, expire one session");
            Collections.sort(sessionInformationList, (o1, o2) -> o1.getLastRequest().compareTo(o2.getLastRequest()));
            sessionInformationList.get(0).expireNow();
        }
    }

    /**
     * @param sessionID
     * @return
     */
    public boolean removeSessionFromSessionRegistry(String sessionID) {
        boolean sessionRemoved = false;
        LOG.debug("Removing session id {} from sessionRegistry", sessionID);
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof User) {
                UserDetails userDetails = (UserDetails) principal;
                for (SessionInformation information : sessionRegistry.getAllSessions(userDetails, false)) {
                    LOG.info("{}={}", sessionID, information.getSessionId());
                    if (sessionID.equals(information.getSessionId())) {
                        information.expireNow();
                        LOG.debug("Removed session id {} from sessionRegistry", sessionID);
                        sessionRemoved = true;
                        sessionRegistry.removeSessionInformation(sessionID);
                    }
                }
            }
        }
        return sessionRemoved;
    }
}
