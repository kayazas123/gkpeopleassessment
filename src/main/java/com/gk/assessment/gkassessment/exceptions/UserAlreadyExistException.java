package com.gk.assessment.gkassessment.exceptions;

/**
 * Created by AYAZ on 13/04/2018.
 */
public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException() {
	super();
    }

    public UserAlreadyExistException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public UserAlreadyExistException(final String message) {
	super(message);
    }

    public UserAlreadyExistException(final Throwable cause) {
	super(cause);
    }

}
