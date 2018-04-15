package com.gk.assessment.gkassessment.utils;

import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.Role;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.web.domain.frontend.BasicAccountPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class UserUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UserUtils.class);

    /**
     * Non instantiable.
     */
    private UserUtils() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Creates a user with basic attributes set.
     * @param username The username.
     * @param email The email.
     * @return A User entity
     */
    public static User createBasicUser(String username, String email) {

        User user = new User();
        user.setUsername(username);
        user.setPassword("secret");
        user.setEmail(email);
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setPhoneNumber("123456789123");
        user.setCountry("ZA");
        user.setEnabled(true);
        return user;
    }

    public static Role createBasicRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }

    public static <T extends BasicAccountPayload> User fromWebUserToDomainUser(T webUser) {
        User user = new User();
        user.setUsername(webUser.getUsername());
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());
        user.setCountry(webUser.getCountry());
        user.setPassword(webUser.getPassword());
        user.setPhoneNumber(webUser.getPhoneNumber());
        user.setEnabled(true);
        return user;
    }


}
