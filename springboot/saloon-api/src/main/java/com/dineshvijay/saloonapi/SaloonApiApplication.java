package com.dineshvijay.saloonapi;

import com.stripe.Stripe;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaAuditing
public class SaloonApiApplication {

	@PostConstruct
	public void setup() {
		Stripe.apiKey = "sk_test_51MlPqdSCcfA0lX7eNkGy3odeoDvThEf3AVuSr0JN5pWnBPSCgEhctxK4nZkOD1NxOYcYEiEt6qJhaSY3BWdZdWcW00NdfRkQTa";
	}
	public static void main(String[] args) {
		SpringApplication.run(SaloonApiApplication.class, args);
	}
}
