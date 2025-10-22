package com.lulobank.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.lulobank.insurance")
public class InsuranceConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsuranceConnectorApplication.class, args);
	}

}
