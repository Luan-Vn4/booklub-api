package br.upe.booklubapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages={"br.upe.booklubapi.infra"},
    repositoryImplementationPostfix="CustomImpl"
)
public class PersistenceConfig {}
