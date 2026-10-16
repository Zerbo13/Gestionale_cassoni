package mattiazerbini.gestionale_cassoni.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) {

        System.out.println("DataInitializer vuoto.");
    }
}