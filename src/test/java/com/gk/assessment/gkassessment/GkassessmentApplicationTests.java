package com.gk.assessment.gkassessment;

import com.gk.assessment.gkassessment.backend.persistence.repositories.UserRepository;
import com.gk.assessment.gkassessment.backend.service.UserService;
import com.gk.assessment.gkassessment.web.dto.UserDTO;
import com.gk.assessment.gkassessment.web.i18n.I18NService;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GkassessmentApplication.class)
@WebAppConfiguration
public class GkassessmentApplicationTests {

    	private static final Logger LOG = LoggerFactory.getLogger(GkassessmentApplicationTests.class);

    	@Autowired
	private UserRepository userRepository;

    	@Autowired
	private UserService userService;

    	@Before
	public void init(){
	    Assert.assertNotNull(userRepository);
	}

	@Test
	public void contextLoads() {
	}

	@Autowired
    	private I18NService i18NService;

    	@Test
    	public void testMessageByLocaleService() throws Exception{
	    String expectedResult = "Bootstrap starter template";
	    String messageId = "index.main.callout";
	    String actual = i18NService.getMessage(messageId);
	    Assert.assertEquals("The actual and expected Strings dont match",expectedResult,actual);
	}

	@Test
    	public void testUserPersist(){

	    UserDTO userDTO = new UserDTO("TestUser","TestFirst","TestLast","TestEmail","email@email.co.za","123456789",true,new Date());
	    //userService.addUser(userDTO);



	}

}
