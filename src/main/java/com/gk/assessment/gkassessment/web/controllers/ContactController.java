package com.gk.assessment.gkassessment.web.controllers;

import com.gk.assessment.gkassessment.web.domain.frontend.FeedbackPojo;

import java.time.Clock;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by AYAZ on 14/04/2018.
 */
@Controller
public class ContactController {
    /**
     * The application logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    /**
     * The key which identifies the feedback payload in the Model.
     */
    public static final String FEEDBACK_MODEL_KEY = "feedback";

    /**
     * The Contact Us view name.
     */
    private static final String CONTACT_US_VIEW_NAME = "contact/contact";

    private static final String GENERIC_ERROR_VIEW_NAME = "/error/genericError";

    private static final String INDEX = "index";


    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGet(ModelMap model) {
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        model.addAttribute(ContactController.FEEDBACK_MODEL_KEY, feedbackPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactPost(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedback) {
        LOG.info("Need to implement email functionality for Feedback POJO content: {}", feedback);
        return ContactController.INDEX;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView signupException(HttpServletRequest request, Exception e) {
        LOG.error("Request {} raised exception {}", request.getRequestURL(), e);
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.addObject("timestamp", LocalDate.now(Clock.systemUTC()));
        mav.setViewName(GENERIC_ERROR_VIEW_NAME);
        return mav;
    }
}
