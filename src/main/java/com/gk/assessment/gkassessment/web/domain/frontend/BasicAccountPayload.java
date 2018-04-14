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
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phonenumber;

    @NotNull
    private String country;

    @NotNull
    private String description;

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

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(final String firstname) {
	this.firstname = firstname;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(final String lastname) {
	this.lastname = lastname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(final String email) {
	this.email = email;
    }

    public String getPhonenumber() {
	return phonenumber;
    }

    public void setPhonenumber(final String phonenumber) {
	this.phonenumber = phonenumber;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(final String country) {
	this.country = country;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(final String description) {
	this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	BasicAccountPayload that = (BasicAccountPayload) o;

	if (!username.equals(that.username)) {
	    return false;
	}
	if (!password.equals(that.password)) {
	    return false;
	}
	if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) {
	    return false;
	}
	if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) {
	    return false;
	}
	if (email != null ? !email.equals(that.email) : that.email != null) {
	    return false;
	}
	if (!phonenumber.equals(that.phonenumber)) {
	    return false;
	}
	return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
	int result = username.hashCode();
	result = 31 * result + password.hashCode();
	result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
	result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
	result = 31 * result + (email != null ? email.hashCode() : 0);
	result = 31 * result + phonenumber.hashCode();
	result = 31 * result + (country != null ? country.hashCode() : 0);
	return result;
    }
}
