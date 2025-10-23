package com.lulobank.insurance;

import com.lulobank.secretsprovider.bootstrap.SecretsProviderBeanFactoryPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.lulobank.insurance")
@Import(SecretsProviderBeanFactoryPostProcessor.class)
public final class InsuranceConnectorApplication {

    private InsuranceConnectorApplication() {
    }

    public static void main(final String[] args) {
        SpringApplication.run(InsuranceConnectorApplication.class, args);
    }

}
