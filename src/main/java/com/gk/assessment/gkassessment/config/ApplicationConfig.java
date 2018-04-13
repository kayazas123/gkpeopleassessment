package com.gk.assessment.gkassessment.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.gk.assessment.gkassessment.backend.persistence.repositories")
@EntityScan("com.gk.assessment.gkassessment.web.domain.frontend")
@EnableTransactionManagement
public class ApplicationConfig {
}
