package com.lulobank.insurance.config;

import com.lulobank.secretsprovider.sdk.client.SecretsProviderClient;
import com.lulobank.secretsprovider.sdk.client.SecretsProviderClientImpl;
import com.lulobank.secretsprovider.sdk.model.SdkConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretProviderConfig {
    @Value("${secrets-provider.sdk.host}")
    private String host;
    @Value("${secrets-provider.sdk.idp-url-token}")
    private String idpUrlToken;
    @Value("${secrets-provider.sdk.idp-client-id}")
    private String clientId;
    @Value("${secrets-provider.sdk.idp-client-secrets}")
    private String clientSecrets;

    private SdkConfiguration getSdkConfiguration() {
        final SdkConfiguration sdkConfiguration = new SdkConfiguration();
        sdkConfiguration.setHost(host);
        sdkConfiguration.setIdpUrlToken(idpUrlToken);
        sdkConfiguration.setClientId(clientId);
        sdkConfiguration.setClientSecret(clientSecrets);
        return sdkConfiguration;
    }
    @Bean
    public SecretsProviderClient secretsProviderClient() {
        return new SecretsProviderClientImpl(getSdkConfiguration());
    }
}
