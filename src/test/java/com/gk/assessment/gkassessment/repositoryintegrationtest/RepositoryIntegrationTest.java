package com.gk.assessment.gkassessment.repositoryintegrationtest;

import com.gk.assessment.gkassessment.GkassessmentApplication;
import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.Role;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.UserRole;
import com.gk.assessment.gkassessment.backend.persistence.repositories.RoleRepository;
import com.gk.assessment.gkassessment.backend.persistence.repositories.UserRepository;
import com.gk.assessment.gkassessment.utils.UserUtils;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by AYAZ on 13/04/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GkassessmentApplication.class)
public class RepositoryIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(RepositoryIntegrationTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testUserPersist(){
	Random random = new Random();
	User user = UserUtils.createBasicUser("gk"+ random.nextInt(500),"test+"+random.nextInt(100)+"+@gk.co.za");
	Set<UserRole> userRoles = new HashSet<>();
	userRoles.add(new UserRole(user,new Role(RolesEnum.BASIC)));
	for(UserRole ur:userRoles){
	    roleRepository.save(ur.getRole());
	    LOG.info("Role persisted {}",ur.getId());
	}

	userRepository.save(user);
	LOG.info("User persisted -> "+user.getId());
    }
}
