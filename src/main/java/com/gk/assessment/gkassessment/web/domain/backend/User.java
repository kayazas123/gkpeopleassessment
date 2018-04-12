package com.gk.assessment.gkassessment.web.domain.backend;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;

    @Column
    private String phone;

    @Column
    private String password;

    public Long getId() {
	return id;
    }

    public void setId(final Long id) {
	this.id = id;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(final String userName) {
	this.userName = userName;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(final String phone) {
	this.phone = phone;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
	if (this == o) {
	    return true;
	}
	if (o == null || getClass() != o.getClass()) {
	    return false;
	}

	User user = (User) o;

	if (id != null ? !id.equals(user.id) : user.id != null) {
	    return false;
	}
	if (userName != null ? !userName.equals(user.userName) : user.userName != null) {
	    return false;
	}
	if (phone != null ? !phone.equals(user.phone) : user.phone != null) {
	    return false;
	}
	return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
	int result = id != null ? id.hashCode() : 0;
	result = 31 * result + (userName != null ? userName.hashCode() : 0);
	result = 31 * result + (phone != null ? phone.hashCode() : 0);
	result = 31 * result + (password != null ? password.hashCode() : 0);
	return result;
    }


}
