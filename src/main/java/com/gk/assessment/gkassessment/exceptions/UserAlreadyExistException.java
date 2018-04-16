package com.gk.assessment.gkassessment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by AYAZ on 13/04/2018.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(final String message) {
	super(message);
    }

}
