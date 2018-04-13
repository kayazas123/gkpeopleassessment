package com.gk.assessment.gkassessment.utils;

import com.gk.assessment.gkassessment.backend.enums.RolesEnum;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.Role;
import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
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
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhoneNumber("123456789123");
        user.setCountry("ZA");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl(null);
        return user;
    }

    public static Role createBasicRole(RolesEnum rolesEnum){
        return new Role(rolesEnum);
    }
}
