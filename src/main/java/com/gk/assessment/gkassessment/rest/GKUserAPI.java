package com.gk.assessment.gkassessment.rest;

import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.Role;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.UserRole;
import com.gk.assessment.gkassessment.backend.service.AuthService;
import com.gk.assessment.gkassessment.backend.service.UserService;
import com.gk.assessment.gkassessment.exceptions.ErrorResponse;
import com.gk.assessment.gkassessment.rest.params.AddUsersRequest;
import com.gk.assessment.gkassessment.rest.params.LoginRequest;
import com.gk.assessment.gkassessment.rest.params.LoginResponse;
import com.gk.assessment.gkassessment.rest.params.UsersResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by AYAZ on 15/04/2018.
 */
@RestController
public class GKUserAPI {

    private static final Logger LOG = LoggerFactory.getLogger(GKUserAPI.class);

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @RequestMapping(value = "/api/users",method = RequestMethod.GET)
    public List<UsersResponse> getUsers(){
	List<User> userList = userService.getUsersList();
	List<UsersResponse> responseList = new ArrayList<>();
	for(User user:userList){
	    UsersResponse response = new UsersResponse();
	    response.setUserName(user.getUsername());
	    response.setFirstName(user.getFirstName());
	    response.setLastName(user.getLastName());
	    response.setEmailAddress(user.getEmail());
	    response.setPhoneNumber(user.getPhoneNumber());
	    responseList.add(response);
	}
	return responseList;
    }

    @RequestMapping(value = "/api/user/login/",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
	LOG.info("Inside login() -> ");
	LoginResponse response = new LoginResponse();
	User user = new User();
	user.setUsername(loginRequest.getUserName());
	user.setPassword(loginRequest.getPassWord());
	try {
	    authService.Authenticate(user);
	}catch (Exception e){
	    LOG.error("Could not login due to",e);
	}
	response.setId("Abc");
	response.setSessionToken("asdfsefdsf");
	return response;
    }


    @RequestMapping(value = "/api/user/add",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody AddUsersRequest addUsersRequest){
	LOG.info("Inside createUser() -> "+addUsersRequest);
	User user = new User();
	user.setUsername(addUsersRequest.getUsername());
	user.setPassword(addUsersRequest.getPassword());
	user.setFirstName(addUsersRequest.getFirstName());
	user.setLastName(addUsersRequest.getLastName());
	user.setCountry(addUsersRequest.getCountry());
	user.setPhoneNumber(addUsersRequest.getPhoneNumber());

	Set<UserRole> userRoles = new HashSet<>();
	userRoles.add(new UserRole(user,new Role(RolesEnum.ADMIN)));
	try {
	    User registeredUser = userService.createUser(user, userRoles);
	    return new ResponseEntity<User>(registeredUser, HttpStatus.OK);

	}catch (ConstraintViolationException cve){
	    LOG.error("User Already Exist",cve);
	}

	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
	return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	ErrorResponse error = new ErrorResponse();
	error.setErrorResponse(HttpStatus.PRECONDITION_FAILED.value());
	error.setMessage(ex.getMessage());
	return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }




}
