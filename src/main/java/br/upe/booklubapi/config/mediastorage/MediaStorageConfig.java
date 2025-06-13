package br.upe.booklubapi.config.mediastorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.net.URI;

@Slf4j
@Configuration
public class MediaStorageConfig {

    @Value("${s3.url}")
    private String url;

    @Value("${s3.access.name}")
    private String accessName;

    @Value("${s3.access.secret}")
    private String accessSecret;

    @Value("${s3.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        final var credentials = StaticCredentialsProvider.create(
            AwsBasicCredentials.create(accessName, accessSecret)
        );

        final Region region = Region.of(this.region);
        log.info("AWS Region: {}", region);

        return S3Client.builder()
            .endpointOverride(URI.create(url))
            .region(region)
            .credentialsProvider(credentials)
            .serviceConfiguration(config -> config
                .pathStyleAccessEnabled(true)
                .build()
            )
            .build();
    }

    @Bean(destroyMethod="close")
    public S3Presigner s3Presigner(S3Client s3Client) {
        final var serviceConfig = s3Client.serviceClientConfiguration();

        return S3Presigner.builder()
            .s3Client(s3Client)
            .region(serviceConfig.region())
            .credentialsProvider(serviceConfig.credentialsProvider())
            .endpointOverride(serviceConfig.endpointOverride().orElse(null))
            .serviceConfiguration(S3Configuration.builder()
                .pathStyleAccessEnabled(true)
                .build())
            .build();
    }

}
