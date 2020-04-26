package com.acms.grocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.acms" })
@EnableJpaRepositories("com.acms.repositories")
@EntityScan(basePackages = { "com.acms" })
public class GroceryApplication {

	public static void main(String[] args) {

		SpringApplication.run(GroceryApplication.class, args);
		// System.out.println("Hello");

	}

}
