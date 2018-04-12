package com.gk.assessment.gkassessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gk.assessment.gkassessment.backend.persistence.repositories")
public class GkassessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(GkassessmentApplication.class, args);
	}
}
