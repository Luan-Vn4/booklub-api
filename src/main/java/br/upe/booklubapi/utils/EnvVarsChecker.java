package br.upe.booklubapi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

@Slf4j
public class EnvVarsChecker implements
        ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    public static final String[] REQUIRED_ENV_VAR = {
        "DB_URL",
        "DB_USERNAME",
        "DB_PASSWORD",
        "KEYCLOAK_CLIENT_ID",
        "KEYCLOAK_CLIENT_SECRET",
        "KEYCLOAK_REALM",
        "KEYCLOAK_AUTH_URL",
        "S3_URL",
        "S3_ACCESS_NAME",
        "S3_ACCESS_SECRET"
    };

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        Environment env = event.getEnvironment();

        for (String envVar : REQUIRED_ENV_VAR) {
            if (
                !env.containsProperty(envVar)
                || env.getProperty(envVar).isBlank()
            ) {
                throw new IllegalStateException(
                    "Missing required environment variable: " + envVar
                );
            }
        }

        log.info("Environment variables checked successfully.");
    }

}
