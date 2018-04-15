package com.gk.assessment.gkassessment.rest.params;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class LoginRequest {

    private String userName;
    private String passWord;

    public LoginRequest() {
    }

    public LoginRequest(final String userName, final String passWord) {
	super();
	this.userName = userName;
	this.passWord = passWord;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getPassWord() {
	return passWord;
    }

    public void setPassWord(final String passWord) {
	this.passWord = passWord;
    }
}
