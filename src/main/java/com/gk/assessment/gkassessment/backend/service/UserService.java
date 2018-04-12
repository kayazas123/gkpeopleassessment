package com.gk.assessment.gkassessment.backend.service;

import com.gk.assessment.gkassessment.backend.persistence.repositories.UserRepository;
import com.gk.assessment.gkassessment.web.domain.backend.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User addUser(User user){
	user = userRepository.save(user);
	return user;
    }

}
