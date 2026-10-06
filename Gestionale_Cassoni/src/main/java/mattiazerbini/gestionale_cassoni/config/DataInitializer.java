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

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            LuogoRepository luogoRepository,
            MezzoRepository mezzoRepository,
            CassoneRepository cassoneRepository
    ) {

        return args -> {

            // =====================================================
            // LUOGHI
            // =====================================================

            if (luogoRepository.count() == 0) {

                Luogo deposito = new Luogo();
                deposito.setNome("Deposito MC Trasporti");
                deposito.setIndirizzo("Via Roma 1");
                deposito.setTipologia("DEPOSITO");
                deposito.setAttivo(true);

                Luogo cantiereRoma = new Luogo();
                cantiereRoma.setNome("Cantiere Roma");
                cantiereRoma.setIndirizzo("Via Appia 120");
                cantiereRoma.setTipologia("CANTIERE");
                cantiereRoma.setAttivo(true);

                Luogo cantiereColleferro = new Luogo();
                cantiereColleferro.setNome("Cantiere Colleferro");
                cantiereColleferro.setIndirizzo("Via Casilina 50");
                cantiereColleferro.setTipologia("CANTIERE");
                cantiereColleferro.setAttivo(true);

                Luogo impiantoColleferro = new Luogo();
                impiantoColleferro.setNome("Impianto Colleferro");
                impiantoColleferro.setIndirizzo("Via Casilina 100");
                impiantoColleferro.setTipologia("IMPIANTO");
                impiantoColleferro.setAttivo(true);

                luogoRepository.save(deposito);
                luogoRepository.save(cantiereRoma);
                luogoRepository.save(cantiereColleferro);
                luogoRepository.save(impiantoColleferro);

                System.out.println("Luoghi di test creati.");
            }


            // =====================================================
            // RECUPERO DEPOSITO
            // =====================================================

            Luogo deposito = luogoRepository
                    .findAll()
                    .stream()
                    .filter(luogo ->
                            luogo.getNome().equalsIgnoreCase(
                                    "Deposito MC Trasporti"
                            )
                    )
                    .findFirst()
                    .orElse(null);


            // =====================================================
            // MEZZI
            // =====================================================

            if (mezzoRepository.count() == 0) {

                Mezzo mezzo1 = new Mezzo();
                mezzo1.setTarga("AB123CD");
                mezzo1.setTipo("Iveco");
                mezzo1.setAttivo(true);

                Mezzo mezzo2 = new Mezzo();
                mezzo2.setTarga("EF456GH");
                mezzo2.setTipo("Iveco");
                mezzo2.setAttivo(true);

                Mezzo mezzo3 = new Mezzo();
                mezzo3.setTarga("IJ789KL");
                mezzo3.setTipo("Mercedes");
                mezzo3.setAttivo(true);

                mezzoRepository.save(mezzo1);
                mezzoRepository.save(mezzo2);
                mezzoRepository.save(mezzo3);

                System.out.println("Mezzi di test creati.");
            }


            // =====================================================
            // CASSONI
            // =====================================================

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

                Cassone cassone4 = new Cassone(
                        "CAS-004",
                        "Aperto",
                        "Giallo",
                        "6 x 2,5 m",
                        true,
                        "30 mc",
                        deposito,
                        LocalDateTime.now()
                );

                Cassone cassone5 = new Cassone(
                        "CAS-005",
                        "Chiuso",
                        "Blu",
                        "5 x 2,5 m",
                        true,
                        "20 mc",
                        deposito,
                        LocalDateTime.now()
                );

                cassoneRepository.save(cassone1);
                cassoneRepository.save(cassone2);
                cassoneRepository.save(cassone3);
                cassoneRepository.save(cassone4);
                cassoneRepository.save(cassone5);

                System.out.println("Cassoni di test creati.");
            }


            // =====================================================
            // SISTEMA CASSONI GIÀ PRESENTI
            // =====================================================

            if (deposito != null) {

                for (Cassone cassone : cassoneRepository.findAll()) {

                    boolean modificato = false;


                    // Se non ha posizione iniziale
                    if (cassone.getPosizioneIniziale() == null) {

                        cassone.setPosizioneIniziale(deposito);
                        cassone.setDataPosizioneIniziale(
                                LocalDateTime.now()
                        );

                        modificato = true;
                    }


                    // Se non ha misura
                    if (cassone.getMisura() == null) {

                        cassone.setMisura("6 x 2,5 m");

                        modificato = true;
                    }


                    // Se non ha capacità
                    if (cassone.getCapacità() == null) {

                        cassone.setCapacità("30 mc");

                        modificato = true;
                    }


                    if (modificato) {

                        cassoneRepository.save(cassone);

                        System.out.println(
                                "Cassone aggiornato: "
                                        + cassone.getCodiceCassone()
                        );
                    }
                }
            }


            System.out.println(
                    "DataInitializer completato."
            );
        };
    }
}