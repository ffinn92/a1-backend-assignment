package com.a1assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class A1assignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(A1assignmentApplication.class, args);
	}

}
