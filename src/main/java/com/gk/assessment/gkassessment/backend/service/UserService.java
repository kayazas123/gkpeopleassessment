package com.gk.assessment.gkassessment.backend.service;

import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.UserRole;
import com.gk.assessment.gkassessment.backend.persistence.repositories.RoleRepository;
import com.gk.assessment.gkassessment.backend.persistence.repositories.UserRepository;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Transactional
    public User  createUser(User user,Set<UserRole> userRoleSet){
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        LOG.info("Persising user {} and roles {} "+user,userRoleSet);
        for(UserRole userRole:userRoleSet){
            roleRepository.save(userRole.getRole());
            LOG.info("Persisted Role -> {}",userRole.getId());
        }
        user = userRepository.save(user);
        LOG.info("Persisted User {} ",user);
        return user;
    }

    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }

    public User findByUserEmail(String email){
        return userRepository.findByEmail(email);
    }

}
