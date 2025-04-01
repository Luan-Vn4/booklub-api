package br.upe.booklubapi.config;

import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MediaStorageConfig {

    @Value("${s3.url}")
    private String url;

    @Value("${s3.access.name}")
    private String accessName;

    @Value("${s3.access.secret}")
    private String accessSecret;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(url)
            .credentials(accessName, accessSecret)
            .build();
    }

    @PostConstruct
    public void startBuckets(MinioClient minioClient) {

    }

}
