package com.gk.assessment.gkassessment.rest.params;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class LogoutResponse {

    public LogoutResponse(final String token) {
	this.token = token;
    }

    private String token;

    public String getToken() {
	return token;
    }

    public void setToken(final String token) {
	this.token = token;
    }
}
