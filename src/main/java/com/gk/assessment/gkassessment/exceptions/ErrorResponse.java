package com.gk.assessment.gkassessment.exceptions;

/**
 * Created by AYAZ on 16/04/2018.
 */
public class ErrorResponse {
	private int errorResponse;
    	private String message;

    public int getErrorResponse() {
	return errorResponse;
    }

    public void setErrorResponse(final int errorResponse) {
	this.errorResponse = errorResponse;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(final String message) {
	this.message = message;
    }
}
