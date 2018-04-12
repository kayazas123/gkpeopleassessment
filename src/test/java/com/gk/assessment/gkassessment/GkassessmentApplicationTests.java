package com.gk.assessment.gkassessment;

import com.gk.assessment.gkassessment.web.i18n.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GkassessmentApplication.class)
@WebAppConfiguration
public class GkassessmentApplicationTests {

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

}
