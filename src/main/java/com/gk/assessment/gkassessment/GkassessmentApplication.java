package com.gk.assessment.gkassessment;

import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.Role;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.UserRole;
import com.gk.assessment.gkassessment.backend.service.UserService;
import com.gk.assessment.gkassessment.utils.UserUtils;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GkassessmentApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(GkassessmentApplication.class);

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(GkassessmentApplication.class, args);
    }

    @Override
    public void run(final String... strings) throws Exception {
        User user = UserUtils.createBasicUser("gk", "admin@gk.co.za");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, new Role(RolesEnum.ADMIN)));
        userService.createUser(user, userRoles);
    }
}
