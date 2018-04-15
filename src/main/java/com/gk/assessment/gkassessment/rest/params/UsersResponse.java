package com.gk.assessment.gkassessment.rest.params;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by AYAZ on 15/04/2018.
 */
@JsonRootName(value = "users")
public class UsersResponse {

    private String userName;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    public String getUserName() {
	return userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(final String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(final String lastName) {
	this.lastName = lastName;
    }

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }
}
