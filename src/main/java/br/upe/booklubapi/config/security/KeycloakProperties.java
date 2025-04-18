package br.upe.booklubapi.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "keycloak")
@Configuration
public class KeycloakProperties {
    
    private String clientUrl;
    private String clientSecret;
    private String clientId;
    private String clientRealm;

    // Getters and Setters

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientRealm() {
        return clientRealm;
    }

    public void setClientRealm(String clientRealm) {
        this.clientRealm = clientRealm;
    }
}
