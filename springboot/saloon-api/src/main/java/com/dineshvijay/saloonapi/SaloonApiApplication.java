package com.dineshvijay.saloonapi;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class SaloonApiApplication {
	@Autowired
	private Environment environment;
	@PostConstruct
	public void setup() {
		Stripe.apiKey = environment.getProperty("stripe_test_key");
	}
	public static void main(String[] args) {
		SpringApplication.run(SaloonApiApplication.class, args);
	}
}
