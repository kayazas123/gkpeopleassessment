package com.gk.assessment.gkassessment.unit;

import com.gk.assessment.gkassessment.backend.persistence.domain.backend.User;
import com.gk.assessment.gkassessment.utils.UserUtils;
import com.gk.assessment.gkassessment.web.domain.frontend.BasicAccountPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by AYAZ on 13/04/2018.
 */
public class UserUtilsUnitTest {

    private MockHttpServletRequest mockHttpServletRequest;

    private PodamFactory podamFactory;

    @Before
    public void init(){
        mockHttpServletRequest = new MockHttpServletRequest();
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    public void testWebUserToDomainUesr(){
        BasicAccountPayload webUser = podamFactory.manufacturePojoWithFullData(BasicAccountPayload.class);
        webUser.setEmail("me@gk.co.za");

        User user = UserUtils.fromWebUserToDomainUser(webUser);
        Assert.assertNotNull(user);

        Assert.assertEquals(webUser.getUsername(),user.getUsername());
        Assert.assertEquals(webUser.getFirstName(),user.getFirstName());
        Assert.assertEquals(webUser.getLastName(),user.getLastName());
        Assert.assertEquals(webUser.getEmail(),user.getEmail());
        Assert.assertEquals(webUser.getPhoneNumber(),user.getPhoneNumber());
        Assert.assertEquals(webUser.getCountry(),user.getCountry());

    }

}
