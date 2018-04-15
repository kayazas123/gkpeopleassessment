package com.gk.assessment.gkassessment.rest.params;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class LoginResponse {
	private String id;
    	private String sessionToken;

    public String getId() {
	return id;
    }

    public void setId(final String id) {
	this.id = id;
    }

    public String getSessionToken() {
	return sessionToken;
    }

    public void setSessionToken(final String sessionToken) {
	this.sessionToken = sessionToken;
    }
}
