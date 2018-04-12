package com.gk.assessment.gkassessment.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Configuration
@Profile("dev")
public class DevelopmentConfig {

    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration() {
	ServletRegistrationBean bean = new ServletRegistrationBean(new WebServlet());
	bean.addUrlMappings("/console/*");
	return bean;
    }
}