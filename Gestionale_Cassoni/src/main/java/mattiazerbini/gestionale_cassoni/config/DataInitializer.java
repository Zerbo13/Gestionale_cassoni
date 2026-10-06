package mattiazerbini.gestionale_cassoni.config;

import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import mattiazerbini.gestionale_cassoni.repositories.LuogoRepository;
import mattiazerbini.gestionale_cassoni.repositories.MezzoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            LuogoRepository luogoRepository,
            MezzoRepository mezzoRepository,
            CassoneRepository cassoneRepository
    ) {
        return args -> {

            if (luogoRepository.count() == 0) {

                Luogo deposito = new Luogo();
                deposito.setNome("Deposito MC Trasporti");
                deposito.setIndirizzo("Via Roma 1");
                deposito.setTipologia("DEPOSITO");
                deposito.setAttivo(true);

                Luogo cantiere1 = new Luogo();
                cantiere1.setNome("Cantiere Roma");
                cantiere1.setIndirizzo("Via Appia 120");
                cantiere1.setTipologia("CANTIERE");
                cantiere1.setAttivo(true);

                Luogo impianto1 = new Luogo();
                impianto1.setNome("Impianto Colleferro");
                impianto1.setIndirizzo("Via Casilina 45");
                impianto1.setTipologia("IMPIANTO");
                impianto1.setAttivo(true);

                luogoRepository.save(deposito);
                luogoRepository.save(cantiere1);
                luogoRepository.save(impianto1);
            }

            if (mezzoRepository.count() == 0) {

                Mezzo mezzo1 = new Mezzo();
                mezzo1.setTarga("AB123CD");
                mezzo1.setTipo("Scarrabile");
                mezzo1.setAttivo(true);

                Mezzo mezzo2 = new Mezzo();
                mezzo2.setTarga("EF456GH");
                mezzo2.setTipo("Scarrabile");
                mezzo2.setAttivo(true);

                mezzoRepository.save(mezzo1);
                mezzoRepository.save(mezzo2);
            }

            if (cassoneRepository.count() == 0) {

                Cassone cassone1 = new Cassone();
                cassone1.setCodiceCassone("CAS-001");
                cassone1.setColore("Blu");
                cassone1.setTipologia("Aperto");
                cassone1.setAttivo(true);

                Cassone cassone2 = new Cassone();
                cassone2.setCodiceCassone("CAS-002");
                cassone2.setColore("Rosso");
                cassone2.setTipologia("Aperto");
                cassone2.setAttivo(true);

                Cassone cassone3 = new Cassone();
                cassone3.setCodiceCassone("CAS-003");
                cassone3.setColore("Verde");
                cassone3.setTipologia("Chiuso");
                cassone3.setAttivo(true);

                cassoneRepository.save(cassone1);
                cassoneRepository.save(cassone2);
                cassoneRepository.save(cassone3);
            }
        };
    }
}