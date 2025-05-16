package br.upe.booklubapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private RestProperties restProperties;

    public WebClientConfig(RestProperties restProperties) {
        this.restProperties = restProperties;
    }

    @Bean
    public WebClient getWebClient(){
        return WebClient.builder().baseUrl(restProperties.getHost()).build();
    }

}