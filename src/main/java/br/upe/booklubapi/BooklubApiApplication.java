package br.upe.booklubapi;

import br.upe.booklubapi.utils.EnvVarsChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooklubApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BooklubApiApplication.class);
        app.addListeners(new EnvVarsChecker());
        app.run(args);
    }

}
