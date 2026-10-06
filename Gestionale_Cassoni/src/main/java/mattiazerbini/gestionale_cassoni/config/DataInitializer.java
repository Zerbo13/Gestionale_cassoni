package mattiazerbini.gestionale_cassoni.config;

import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import mattiazerbini.gestionale_cassoni.repositories.LuogoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            LuogoRepository luogoRepository,
            CassoneRepository cassoneRepository
    ) {
        return args -> {

            Luogo deposito;

            if (luogoRepository.count() == 0) {

                deposito = new Luogo();
                deposito.setNome("Deposito MC Trasporti");
                deposito.setIndirizzo("Via Roma 1");
                deposito.setTipologia("DEPOSITO");
                deposito.setAttivo(true);

                Luogo cantiere = new Luogo();
                cantiere.setNome("Cantiere Roma");
                cantiere.setIndirizzo("Via Appia 120");
                cantiere.setTipologia("CANTIERE");
                cantiere.setAttivo(true);

                Luogo impianto = new Luogo();
                impianto.setNome("Impianto Colleferro");
                impianto.setIndirizzo("Via Casilina 45");
                impianto.setTipologia("IMPIANTO");
                impianto.setAttivo(true);

                deposito = luogoRepository.save(deposito);
                luogoRepository.save(cantiere);
                luogoRepository.save(impianto);

            } else {
                deposito = luogoRepository.findAll().get(0);
            }

            if (cassoneRepository.count() == 0) {

                Cassone cassone1 = new Cassone(
                        "CAS-001",
                        "Aperto",
                        "Blu",
                        "6 x 2,5 m",
                        true,
                        "30 mc",
                        deposito,
                        LocalDateTime.now()
                );

                Cassone cassone2 = new Cassone(
                        "CAS-002",
                        "Aperto",
                        "Rosso",
                        "6 x 2,5 m",
                        true,
                        "30 mc",
                        deposito,
                        LocalDateTime.now()
                );

                Cassone cassone3 = new Cassone(
                        "CAS-003",
                        "Chiuso",
                        "Verde",
                        "5 x 2,5 m",
                        true,
                        "20 mc",
                        deposito,
                        LocalDateTime.now()
                );

                cassoneRepository.save(cassone1);
                cassoneRepository.save(cassone2);
                cassoneRepository.save(cassone3);
            }
        };
    }
}