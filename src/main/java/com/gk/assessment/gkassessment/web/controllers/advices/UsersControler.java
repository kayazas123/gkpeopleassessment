package com.gk.assessment.gkassessment.web.controllers.advices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Controller
public class UsersControler {
    public static final String USER_VIEW_NAME = "user/users";

    @RequestMapping("/users")
    public String getUserUrl(){
	return USER_VIEW_NAME;
    }

}
