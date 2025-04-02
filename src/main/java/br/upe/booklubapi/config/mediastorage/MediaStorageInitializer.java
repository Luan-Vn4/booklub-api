package br.upe.booklubapi.config.mediastorage;

import br.upe.booklubapi.domain.core.gateways.mediastorage.MediaStorageGateway;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class MediaStorageInitializer
        implements ApplicationListener<ApplicationReadyEvent> {

    private final MediaStorageGateway gateway;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createBuckets();
    }

    private void createBuckets() {
        final var buckets = List.of("images");
        log.info("Creating media storage buckets");
        for (final String bucket : buckets) {
            boolean created = gateway.createBucketIfNotExists("images");
            if (created) {
                log.info("Created media storage bucket \"{}\"", bucket);
                continue;
            }
            log.info("Media storage bucket \"{}\" already exists", bucket);
        }
        log.info("Buckets creation completed");
    }

}
