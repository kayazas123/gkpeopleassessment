package com.gk.assessment.gkassessment.backend.service;

import java.io.Serializable;

/**
 * Created by AYAZ on 13/04/2018.
 */
public class JwtAuthenticationRequest implements Serializable{
    private String username;
    private String password;

    public JwtAuthenticationRequest() {
	super();
    }

    public JwtAuthenticationRequest(String username, String password) {
	this.setUsername(username);
	this.setPassword(password);
    }

    public String getUsername() {
	return this.username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(String password) {
	this.password = password;
    }
}
