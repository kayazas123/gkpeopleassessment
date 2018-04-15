package com.gk.assessment.gkassessment.web.domain.frontend;

/**
 * Created by AYAZ on 14/04/2018.
 */
public class FeedbackPojo {
    private String email;
    private String firstName;
    private String lastName;
    private String feedback;

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getFeedback() {
	return feedback;
    }

    public void setFeedback(String feedback) {
	this.feedback = feedback;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder("FeedbackPojo{");
	sb.append("email='").append(email).append('\'');
	sb.append(", firstName='").append(firstName).append('\'');
	sb.append(", lastName='").append(lastName).append('\'');
	sb.append(", feedback='").append(feedback).append('\'');
	sb.append('}');
	return sb.toString();
    }

}
