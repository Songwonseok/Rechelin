package com.web.curation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class WebCurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebCurationApplication.class, args);
	}

}
