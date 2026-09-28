package mattiazerbini.gestionale_cassoni.config;

import mattiazerbini.gestionale_cassoni.entities.Ruolo;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.repositories.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer implements CommandLineRunner {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UtenteRepository utenteRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        // ADMIN
        if (!utenteRepository.existsByNickname("Admin")) {

            Utente admin = new Utente();

            admin.setNome("Admin");
            admin.setCognome("Sistema");
            admin.setNickname("Admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRuolo(Ruolo.ADMIN);
            admin.setAttivo(true);

            utenteRepository.save(admin);
        }

        // OPERAIO 1
        if (!utenteRepository.existsByNickname("Mario Rossi")) {

            Utente mario = new Utente();

            mario.setNome("Mario");
            mario.setCognome("Rossi");
            mario.setNickname("Mario Rossi");
            mario.setPassword(passwordEncoder.encode("mario123"));
            mario.setRuolo(Ruolo.OPERAIO);
            mario.setAttivo(true);

            utenteRepository.save(mario);
        }

        // OPERAIO 2
        if (!utenteRepository.existsByNickname("Luca Bianchi")) {

            Utente luca = new Utente();

            luca.setNome("Luca");
            luca.setCognome("Bianchi");
            luca.setNickname("Luca Bianchi");
            luca.setPassword(passwordEncoder.encode("luca123"));
            luca.setRuolo(Ruolo.OPERAIO);
            luca.setAttivo(true);

            utenteRepository.save(luca);
        }
    }
}