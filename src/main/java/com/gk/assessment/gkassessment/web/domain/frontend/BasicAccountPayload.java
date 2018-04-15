package com.gk.assessment.gkassessment.web.domain.frontend;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Created by AYAZ on 13/04/2018.
 */
public class BasicAccountPayload implements Serializable{

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String confirmPassword;


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String country;

    public String getUsername() {
	return username;
    }

    public void setUsername(final String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    public String getConfirmPassword() {
	return confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword) {
	this.confirmPassword = confirmPassword;
    }


    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(final String country) {
	this.country = country;
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

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
	return "BasicAccountPayload{" +
	    "username='" + username + '\'' +
	    ", firstName='" + firstName + '\'' +
	    ", lastName='" + lastName + '\'' +
	    ", email='" + email + '\'' +
	    ", phoneNumber='" + phoneNumber + '\'' +
	    ", country='" + country + '\'' +
	    '}';
    }
}
