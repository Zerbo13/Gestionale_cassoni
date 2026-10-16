package mattiazerbini.gestionale_cassoni.config;

import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import mattiazerbini.gestionale_cassoni.repositories.LuogoRepository;
import mattiazerbini.gestionale_cassoni.repositories.MezzoRepository;
import mattiazerbini.gestionale_cassoni.repositories.UtenteRepository;
import mattiazerbini.gestionale_cassoni.repositories.ViaggioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ViaggioRepository viaggioRepository;
    private final UtenteRepository utenteRepository;
    private final CassoneRepository cassoneRepository;
    private final MezzoRepository mezzoRepository;
    private final LuogoRepository luogoRepository;


    public DataInitializer(
            ViaggioRepository viaggioRepository,
            UtenteRepository utenteRepository,
            CassoneRepository cassoneRepository,
            MezzoRepository mezzoRepository,
            LuogoRepository luogoRepository
    ) {
        this.viaggioRepository = viaggioRepository;
        this.utenteRepository = utenteRepository;
        this.cassoneRepository = cassoneRepository;
        this.mezzoRepository = mezzoRepository;
        this.luogoRepository = luogoRepository;
    }


    @Override
    public void run(String... args) {

        System.out.println("=================================");
        System.out.println("AVVIO PULIZIA COMPLETA DATABASE");
        System.out.println("=================================");


        viaggioRepository.deleteAll();

        System.out.println(
                "Viaggi eliminati."
        );


        cassoneRepository.deleteAll();

        System.out.println(
                "Cassoni eliminati."
        );


        utenteRepository.deleteAll();

        System.out.println(
                "Utenti eliminati."
        );


        mezzoRepository.deleteAll();

        System.out.println(
                "Mezzi eliminati."
        );


        luogoRepository.deleteAll();

        System.out.println(
                "Luoghi eliminati."
        );


        System.out.println(
                "================================="
        );

        System.out.println(
                "DATABASE COMPLETAMENTE SVUOTATO."
        );

        System.out.println(
                "================================="
        );
    }
}