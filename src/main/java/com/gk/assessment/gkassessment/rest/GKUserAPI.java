package com.gk.assessment.gkassessment.rest;

import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.Role;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.UserRole;
import com.gk.assessment.gkassessment.backend.service.AuthService;
import com.gk.assessment.gkassessment.backend.service.UserService;
import com.gk.assessment.gkassessment.exceptions.UserAlreadyExistException;
import com.gk.assessment.gkassessment.exceptions.UserNotFoundException;
import com.gk.assessment.gkassessment.registry.UsersSessionRegistry;
import com.gk.assessment.gkassessment.rest.params.AddUsersRequest;
import com.gk.assessment.gkassessment.rest.params.LoginRequest;
import com.gk.assessment.gkassessment.rest.params.LoginResponse;
import com.gk.assessment.gkassessment.rest.params.LogoutRequest;
import com.gk.assessment.gkassessment.rest.params.LogoutResponse;
import com.gk.assessment.gkassessment.rest.params.UsersResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
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
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsersSessionRegistry usersSessionRegistry;

    @RequestMapping(value = "/api/users/user/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public UsersResponse getUser(@PathVariable Long id){
	Optional<User> user = userService.getUser(id);
	if(!user.isPresent()){
	    throw new UserNotFoundException("User id "+id+" not found");
	}
	return mapResponse(user.get());
    }


    @RequestMapping(value = "/api/users",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsersResponse> getUsers(){
	List<User> userList = userService.getUsersList();
	List<UsersResponse> responseList = new ArrayList<>();
	for(User user:userList){
	    responseList.add(mapResponse(user));
	}
	return responseList;
    }


    @RequestMapping(value = "/api/user/login",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest loginRequest){

	LoginResponse loginResponse = new LoginResponse();
	User user = new User();
	user.setUsername(loginRequest.getUserName());
	user.setPassword(loginRequest.getPassWord());
	try {
	    Authentication auth = new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
	    Authentication authenticate = authService.authenticate(auth);
	    LOG.info("Is Authenticated=",auth.isAuthenticated());
	    //authService.login(request,response,loginRequest.getUserName(),loginRequest.getPassWord());
	}catch (Exception e){
	    LOG.error("Could not login due to",e);
	}
	return loginResponse;
    }

    @RequestMapping(value = "/api/user/logout",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public LogoutResponse logout(@RequestBody LogoutRequest session_token){
	boolean isExpired = usersSessionRegistry.removeSessionFromSessionRegistry(session_token.getToken());
	LOG.debug("Session expired={}",isExpired);
	return new LogoutResponse(session_token.getToken());
    }

    @RequestMapping(value = "/api/user/add",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody AddUsersRequest addUsersRequest){
	User user = new User();
	user.setUsername(addUsersRequest.getUsername());
	user.setPassword(addUsersRequest.getPassword());
	user.setFirstName(addUsersRequest.getFirstName());
	user.setLastName(addUsersRequest.getLastName());
	user.setCountry(addUsersRequest.getCountry());
	user.setPhoneNumber(addUsersRequest.getPhoneNumber());

	Set<UserRole> userRoles = new HashSet<>();
	userRoles.add(new UserRole(user,new Role(RolesEnum.BASIC)));
	try {
	    User registeredUser = userService.createUser(user, userRoles);
	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
	    LOG.debug("location uri created");
	    return ResponseEntity.created(location).build();
	}catch (ConstraintViolationException cve){
	    LOG.error("User Already Exist",cve);
	    throw new UserAlreadyExistException("id - "+addUsersRequest.getUsername());
	}catch (DataIntegrityViolationException dive){
	    LOG.error("User Already Exist",dive);
	    throw new UserAlreadyExistException("id - "+addUsersRequest.getUsername());
	}
    }

    private UsersResponse mapResponse(User user){
	UsersResponse response = new UsersResponse();
	response.setUserName(user.getUsername());
	response.setFirstName(user.getFirstName());
	response.setLastName(user.getLastName());
	response.setEmailAddress(user.getEmail());
	response.setPhoneNumber(user.getPhoneNumber());
	return response;
    }

}
