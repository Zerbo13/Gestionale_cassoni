package mattiazerbini.gestionale_cassoni.config;

import mattiazerbini.gestionale_cassoni.entities.*;
import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import mattiazerbini.gestionale_cassoni.repositories.LuogoRepository;
import mattiazerbini.gestionale_cassoni.repositories.MezzoRepository;
import mattiazerbini.gestionale_cassoni.repositories.UtenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UtenteRepository utenteRepository;
    private final MezzoRepository mezzoRepository;
    private final CassoneRepository cassoneRepository;
    private final LuogoRepository luogoRepository;
    private final PasswordEncoder passwordEncoder;


    // =====================================================
    // LUOGHI DISPONIBILI ANCHE NEL BLOCCO CASSONI
    // =====================================================

    private Luogo luogo1;
    private Luogo luogo2;
    private Luogo luogo3;
    private Luogo luogo4;
    private Luogo luogo5;
    private Luogo luogo6;
    private Luogo luogo7;
    private Luogo luogo8;
    private Luogo luogo9;
    private Luogo luogo10;

    private Luogo luogo11;
    private Luogo luogo12;
    private Luogo luogo13;
    private Luogo luogo14;
    private Luogo luogo15;
    private Luogo luogo16;
    private Luogo luogo17;
    private Luogo luogo18;
    private Luogo luogo19;
    private Luogo luogo20;

    private Luogo luogo21;
    private Luogo luogo22;
    private Luogo luogo23;
    private Luogo luogo24;
    private Luogo luogo25;
    private Luogo luogo26;
    private Luogo luogo27;
    private Luogo luogo28;
    private Luogo luogo29;
    private Luogo luogo30;

    private Luogo luogo31;
    private Luogo luogo32;
    private Luogo luogo33;
    private Luogo luogo34;
    private Luogo luogo35;
    private Luogo luogo36;
    private Luogo luogo37;
    private Luogo luogo38;
    private Luogo luogo39;
    private Luogo luogo40;

    private Luogo luogo41;
    private Luogo luogo42;
    private Luogo luogo43;
    private Luogo luogo44;
    private Luogo luogo45;
    private Luogo luogo46;
    private Luogo luogo47;
    private Luogo luogo48;
    private Luogo luogo49;
    private Luogo luogo50;
    private Luogo luogo51;
    private Luogo luogo52;
    private Luogo luogo53;
    private Luogo luogo54;
    private Luogo luogo55;
    private Luogo luogo56;
    private Luogo luogo57;
    private Luogo luogo58;
    private Luogo luogo59;


    public DataInitializer(
            UtenteRepository utenteRepository,
            MezzoRepository mezzoRepository,
            CassoneRepository cassoneRepository,
            LuogoRepository luogoRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.utenteRepository = utenteRepository;
        this.mezzoRepository = mezzoRepository;
        this.cassoneRepository = cassoneRepository;
        this.luogoRepository = luogoRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {


        // =====================================================
        // LUOGHI
        // =====================================================

        if (luogoRepository.count() == 0) {


            // =================================================
            // DEPOSITO
            // =================================================

            Luogo piazzale = new Luogo();
            piazzale.setNome("PIAZZALE");
            piazzale.setIndirizzo("VIA CIRCONVALLAZIONE VALLI");
            piazzale.setTipologia("");
            piazzale.setAttivo(true);


            // =================================================
            // PE GENERAL CONTRACTOR SRL
            // =================================================

            luogo1 = new Luogo();
            luogo1.setNome("PE GENERAL CONTRACTOR SRL");
            luogo1.setIndirizzo("STRADA PROVINCIALE ARTENA - GIULIANELLO (GIULIANELLO GIANLUCA)");
            luogo1.setTipologia("");
            luogo1.setAttivo(true);

            luogo2 = new Luogo();
            luogo2.setNome("PE GENERAL CONTRACTOR SRL");
            luogo2.setIndirizzo("VIA BRACCIANESE CLAUDIA");
            luogo2.setTipologia("DANILO - 3293357673");
            luogo2.setAttivo(true);

            luogo3 = new Luogo();
            luogo3.setNome("PE GENERAL CONTRACTOR SRL");
            luogo3.setIndirizzo("CASERMA ETTORE ROSSO");
            luogo3.setTipologia("ANDREA - 3208884528");
            luogo3.setAttivo(true);

            luogo4 = new Luogo();
            luogo4.setNome("PE GENERAL CONTRACTOR SRL");
            luogo4.setIndirizzo("VIA FRANCO MEZZADRA");
            luogo4.setTipologia("ANTONIO BPO - 3442571105");
            luogo4.setAttivo(true);

            luogo5 = new Luogo();
            luogo5.setNome("PE GENERAL CONTRACTOR SRL");
            luogo5.setIndirizzo("VIA ETTORE STAMPINI");
            luogo5.setTipologia("");
            luogo5.setAttivo(true);

            luogo6 = new Luogo();
            luogo6.setNome("PE GENERAL CONTRACTOR SRL");
            luogo6.setIndirizzo("VIA VITINIA");
            luogo6.setTipologia("PINO - 3713545818");
            luogo6.setAttivo(true);


            // =================================================
            // BPC SRL
            // =================================================

            luogo7 = new Luogo();
            luogo7.setNome("BPC SRL");
            luogo7.setIndirizzo("VIA ROCCO SANTOLIQUIDO");
            luogo7.setTipologia("FABIO - 3426222692");
            luogo7.setAttivo(true);

            luogo8 = new Luogo();
            luogo8.setNome("BPC SRL");
            luogo8.setIndirizzo("VIA GENNARO PASQUARELLO");
            luogo8.setTipologia("");
            luogo8.setAttivo(true);


            // =================================================
            // RXP
            // =================================================

            luogo9 = new Luogo();
            luogo9.setNome("RXP");
            luogo9.setIndirizzo("VIALE CARSO");
            luogo9.setTipologia("");
            luogo9.setAttivo(true);


            // =================================================
            // PHOENIX SRL
            // =================================================

            luogo10 = new Luogo();
            luogo10.setNome("PHOENIX SRL");
            luogo10.setIndirizzo("VIA MACCARESE");
            luogo10.setTipologia("");
            luogo10.setAttivo(true);

            luogo11 = new Luogo();
            luogo11.setNome("PHOENIX SRL");
            luogo11.setIndirizzo("STRADA PROVINCIALE ARTENA - GIULIANELLO (GIULIANELLO GIANLUCA)");
            luogo11.setTipologia("");
            luogo11.setAttivo(true);


            // =================================================
            // CONSORZIO OCEANIA
            // =================================================

            luogo12 = new Luogo();
            luogo12.setNome("CONSORZIO OCEANIA");
            luogo12.setIndirizzo("SP 3 VITERBO");
            luogo12.setTipologia("");
            luogo12.setAttivo(true);


            // =================================================
            // FCP INFRASTRUCTURE SRL
            // =================================================

            luogo13 = new Luogo();
            luogo13.setNome("FCP INFRASTRUCTURE SRL");
            luogo13.setIndirizzo("STRADA PROVINCIALE ARTENA - GIULIANELLO (GIULIANELLO GIANLUCA)");
            luogo13.setTipologia("");
            luogo13.setAttivo(true);

            luogo14 = new Luogo();
            luogo14.setNome("FCP INFRASTRUCTURE SRL");
            luogo14.setIndirizzo("VIALE DEI 4 VENTI");
            luogo14.setTipologia("SAVERIO - 3389072609");
            luogo14.setAttivo(true);


            // =================================================
            // EDIL MAS SRL
            // =================================================

            luogo15 = new Luogo();
            luogo15.setNome("EDIL MAS SRL");
            luogo15.setIndirizzo("VIA ANTO GIULIO BARRILI");
            luogo15.setTipologia("MARCO - 3358713835");
            luogo15.setAttivo(true);

            luogo16 = new Luogo();
            luogo16.setNome("EDIL MAS SRL");
            luogo16.setIndirizzo("STRADA PROVINCIALE ARTENA - GIULIANELLO (GIULIANELLO MANOLO)");
            luogo16.setTipologia("");
            luogo16.setAttivo(true);


            // =================================================
            // GAMMA SRL UNIPERSONALE IMPRESA EDILE STRADALE
            // =================================================

            luogo17 = new Luogo();
            luogo17.setNome("GAMMA SRL UNIPERSONALE IMPRESA EDILE STRADALE");
            luogo17.setIndirizzo("VIA 24 MAGGIO GROTTAFERRATA");
            luogo17.setTipologia("ALFREDO - 3924228358");
            luogo17.setAttivo(true);


            // =================================================
            // GRANDI LAVORI
            // =================================================

            luogo18 = new Luogo();
            luogo18.setNome("GRANDI LAVORI");
            luogo18.setIndirizzo("PARCO CELIO");
            luogo18.setTipologia("MATTEO - 3881645697");
            luogo18.setAttivo(true);

            luogo19 = new Luogo();
            luogo19.setNome("GRANDI LAVORI");
            luogo19.setIndirizzo("PIAZZA SIDEREA");
            luogo19.setTipologia("");
            luogo19.setAttivo(true);

            luogo20 = new Luogo();
            luogo20.setNome("GRANDI LAVORI");
            luogo20.setIndirizzo("VIA S. NICOLA");
            luogo20.setTipologia("GIANLUCA - 3890370849");
            luogo20.setAttivo(true);

            luogo21 = new Luogo();
            luogo21.setNome("GRANDI LAVORI");
            luogo21.setIndirizzo("VIA MAGNAGRECIA");
            luogo21.setTipologia("");
            luogo21.setAttivo(true);


            // =================================================
            // MA.GI.B SRL
            // =================================================

            luogo22 = new Luogo();
            luogo22.setNome("MA.GI.B SRL");
            luogo22.setIndirizzo("VIA DIANA");
            luogo22.setTipologia("");
            luogo22.setAttivo(true);


            // =================================================
            // APULIA
            // =================================================

            luogo23 = new Luogo();
            luogo23.setNome("APULIA");
            luogo23.setIndirizzo("VIA DELIA");
            luogo23.setTipologia("EMANUELE - 3482904942");
            luogo23.setAttivo(true);


            // =================================================
            // GI.FE COSTRUZIONI
            // =================================================

            luogo24 = new Luogo();
            luogo24.setNome("GI.FE COSTRUZIONI");
            luogo24.setIndirizzo("VIA G. MATTEOTTI CARPINETO");
            luogo24.setTipologia("");
            luogo24.setAttivo(true);


            // =================================================
            // GREEN PARK
            // =================================================

            luogo25 = new Luogo();
            luogo25.setNome("GREEN PARK");
            luogo25.setIndirizzo("VIA ZARA");
            luogo25.setTipologia("");
            luogo25.setAttivo(true);


            // =================================================
            // EDIL CASA COOPERATIVA
            // =================================================

            luogo26 = new Luogo();
            luogo26.setNome("EDIL CASA COOPERATIVA");
            luogo26.setIndirizzo("VIA ISIDORO ZINGARETTI");
            luogo26.setTipologia("");
            luogo26.setAttivo(true);


            // =================================================
            // SAS LAVORI
            // =================================================

            luogo27 = new Luogo();
            luogo27.setNome("SAS LAVORI");
            luogo27.setIndirizzo("PONTE DEL RISORGIMENTO");
            luogo27.setTipologia("FABRIZIO - 3501543917");
            luogo27.setAttivo(true);

            luogo28 = new Luogo();
            luogo28.setNome("SAS LAVORI");
            luogo28.setIndirizzo("VIA ANTONINO BONGIORNO");
            luogo28.setTipologia("");
            luogo28.setAttivo(true);

            luogo29 = new Luogo();
            luogo29.setNome("SAS LAVORI");
            luogo29.setIndirizzo("GIULIANELLO");
            luogo29.setTipologia("");
            luogo29.setAttivo(true);


            // =================================================
            // C.I.G.A.L.
            // =================================================

            luogo30 = new Luogo();
            luogo30.setNome("C.I.G.A.L.");
            luogo30.setIndirizzo("AVIO");
            luogo30.setTipologia("MAURO - 3386988537");
            luogo30.setAttivo(true);


            // =================================================
            // APPALTI SRL
            // =================================================

            luogo31 = new Luogo();
            luogo31.setNome("APPALTI SRL");
            luogo31.setIndirizzo("VIA ROMANIA ALBANO LAZIALE");
            luogo31.setTipologia("LUIGI - 3459830037");
            luogo31.setAttivo(true);


            // =================================================
            // LEONE ARMANDO
            // =================================================

            luogo32 = new Luogo();
            luogo32.setNome("LEONE ARMANDO");
            luogo32.setIndirizzo("VIA GOITO");
            luogo32.setTipologia("ROBERTA - 3203853616");
            luogo32.setAttivo(true);

            luogo33 = new Luogo();
            luogo33.setNome("LEONE ARMANDO");
            luogo33.setIndirizzo("VIA OLIVELLA ALBANO");
            luogo33.setTipologia("");
            luogo33.setAttivo(true);


            // =================================================
            // PATRIZIO COSTRUZIONI
            // =================================================

            luogo34 = new Luogo();
            luogo34.setNome("PATRIZIO COSTRUZIONI");
            luogo34.setIndirizzo("VIA CUOCO");
            luogo34.setTipologia("ALESSANDRO - 3934327819");
            luogo34.setAttivo(true);


            // =================================================
            // HYDRO ADC
            // =================================================

            luogo35 = new Luogo();
            luogo35.setNome("HYDRO ADC");
            luogo35.setIndirizzo("PZZ S GIOVANNI");
            luogo35.setTipologia("CRISTIAN - 3298286216");
            luogo35.setAttivo(true);

            luogo36 = new Luogo();
            luogo36.setNome("HYDRO ADC");
            luogo36.setIndirizzo("P.ZZA DELL'UNITA'");
            luogo36.setTipologia("");
            luogo36.setAttivo(true);


            // =================================================
            // TECNOFIBER
            // =================================================

            luogo37 = new Luogo();
            luogo37.setNome("TECNOFIBER");
            luogo37.setIndirizzo("VIA NOCCIANO");
            luogo37.setTipologia("EMILIANO - 3886986040");
            luogo37.setAttivo(true);


            // =================================================
            // RETE REALIZZAZIONE
            // =================================================

            luogo38 = new Luogo();
            luogo38.setNome("RETE REALIZZAZIONE");
            luogo38.setIndirizzo("VIA ANAGNINA");
            luogo38.setTipologia("");
            luogo38.setAttivo(true);

            luogo39 = new Luogo();
            luogo39.setNome("RETE REALIZZAZIONE");
            luogo39.setIndirizzo("VIA GRAZIA DELEDDA");
            luogo39.setTipologia("");
            luogo39.setAttivo(true);


            // =================================================
            // CONSORZIO STABILE DI VENERE
            // =================================================

            luogo40 = new Luogo();
            luogo40.setNome("CONSORZIO STABILE DI VENERE");
            luogo40.setIndirizzo("PIAZZA 500");
            luogo40.setTipologia("");
            luogo40.setAttivo(true);


            // =================================================
            // SMART COSTRUIRE
            // =================================================

            luogo41 = new Luogo();
            luogo41.setNome("SMART COSTRUIRE");
            luogo41.setIndirizzo("LUNGO MARE TUSCANELLI");
            luogo41.setTipologia("");
            luogo41.setAttivo(true);


            // =================================================
            // FRA.VIO COSTRUZIONI
            // =================================================

            luogo42 = new Luogo();
            luogo42.setNome("FRA.VIO COSTRUZIONI");
            luogo42.setIndirizzo("VIA VLLE RITA CORI");
            luogo42.setTipologia("");
            luogo42.setAttivo(true);


            // =================================================
            // IMPERIAL
            // =================================================

            luogo43 = new Luogo();
            luogo43.setNome("IMPERIAL");
            luogo43.setIndirizzo("VIA ALESSANDRO RUSPOLI");
            luogo43.setTipologia("DIEGO - 3278828749");
            luogo43.setAttivo(true);


            // =================================================
            // ARTENA MARMI
            // =================================================

            luogo44 = new Luogo();
            luogo44.setNome("ARTENA MARMI");
            luogo44.setIndirizzo("IV KM COLLEFERRO");
            luogo44.setTipologia("ANGELO - 3331423773");
            luogo44.setAttivo(true);


            // =================================================
            // PM EDILIZIA
            // =================================================

            luogo45 = new Luogo();
            luogo45.setNome("PM EDILIZIA");
            luogo45.setIndirizzo("VIA S. GIOVANNI IN ARGENTELLA");
            luogo45.setTipologia("");
            luogo45.setAttivo(true);

            luogo46 = new Luogo();
            luogo46.setNome("PM EDILIZIA");
            luogo46.setIndirizzo("VIA ROIATE");
            luogo46.setTipologia("");
            luogo46.setAttivo(true);


            // =================================================
            // AV COSTRUZIONI
            // =================================================

            luogo47 = new Luogo();
            luogo47.setNome("AV COSTRUZIONI");
            luogo47.setIndirizzo("VIA VALLE DELL'OSTE ARTENA");
            luogo47.setTipologia("");
            luogo47.setAttivo(true);


            // =================================================
            // BIONDI
            // =================================================

            luogo48 = new Luogo();
            luogo48.setNome("BIONDI");
            luogo48.setIndirizzo("VIA TORRITA");
            luogo48.setTipologia("RICCARDO - 3471144468");
            luogo48.setAttivo(true);


            // =================================================
            // FRELO SRLS
            // =================================================

            luogo49 = new Luogo();
            luogo49.setNome("FRELO SRLS");
            luogo49.setIndirizzo("VIA DELLE BALENIERE");
            luogo49.setTipologia("OTELLO - 3920890725");
            luogo49.setAttivo(true);

            // =================================================
            // CERCHIO CHIUSO
            // =================================================

            luogo50 = new Luogo();
            luogo50.setNome("CERCHIO CHIUSO");
            luogo50.setIndirizzo("VIA DELLA PISANA");
            luogo50.setTipologia("");
            luogo50.setAttivo(true);


            // =================================================
            // PE GENERAL CONTRACTOR SR
            // =================================================

            luogo51 = new Luogo();
            luogo51.setNome("PE GENERAL CONTRACTOR SRL");
            luogo51.setIndirizzo("VIA SANTI COSMA E DAMIANO");
            luogo51.setTipologia(" ");
            luogo51.setAttivo(true);

            luogo52 = new Luogo();
            luogo52.setNome("GRUPPO CACAVALE");
            luogo52.setIndirizzo("VIA SANTA LUCIA");
            luogo52.setTipologia(" ");
            luogo52.setAttivo(true);

            luogo53 = new Luogo();
            luogo53.setNome("BCP SRL");
            luogo53.setIndirizzo("STRADA PROVINCIALE ARTENA - GIULIANELLO (GIULIANELLO GIANLUCA)");
            luogo53.setTipologia(" ");
            luogo53.setAttivo(true);

            luogo54 = new Luogo();
            luogo54.setNome("COSTRUZIONI GENERALI");
            luogo54.setIndirizzo("SS 14 FERENTINO");
            luogo54.setTipologia(" ");
            luogo54.setAttivo(true);

            luogo55 = new Luogo();
            luogo55.setNome("SERVIZI INTEGRALI");
            luogo55.setIndirizzo("VIA BASILICATA - TERRACINA");
            luogo55.setTipologia(" ");
            luogo55.setAttivo(true);

            luogo56 = new Luogo();
            luogo56.setNome("PHOENIX SRL");
            luogo56.setIndirizzo("VIA ANTRODOCO");
            luogo56.setTipologia(" ");
            luogo56.setAttivo(true);

            luogo57 = new Luogo();
            luogo57.setNome("STEDIMA");
            luogo57.setIndirizzo("VIA MICHELINA");
            luogo57.setTipologia(" ");
            luogo57.setAttivo(true);

            luogo58 = new Luogo();
            luogo58.setNome("GRANDI LAVORI");
            luogo58.setIndirizzo("LARGO BELTRAMELLI");
            luogo58.setTipologia(" ");
            luogo58.setAttivo(true);

            luogo59 = new Luogo();
            luogo59.setNome("LEONE ARMANDO");
            luogo59.setIndirizzo("VIA SAN MICHELE - NEMI");
            luogo59.setTipologia(" ");
            luogo59.setAttivo(true);






            luogoRepository.saveAll(
                    List.of(
                            piazzale,

                            luogo1, luogo2, luogo3, luogo4, luogo5,
                            luogo6, luogo7, luogo8, luogo9, luogo10,

                            luogo11, luogo12, luogo13, luogo14, luogo15,
                            luogo16, luogo17, luogo18, luogo19, luogo20,

                            luogo21, luogo22, luogo23, luogo24, luogo25,
                            luogo26, luogo27, luogo28, luogo29, luogo30,

                            luogo31, luogo32, luogo33, luogo34, luogo35,
                            luogo36, luogo37, luogo38, luogo39, luogo40,

                            luogo41, luogo42, luogo43, luogo44, luogo45,
                            luogo46, luogo47, luogo48, luogo49, luogo50,

                            luogo51, luogo52, luogo53, luogo54, luogo55,
                            luogo56, luogo57, luogo58, luogo59
                    )
            );

            System.out.println("Luoghi iniziali creati.");
        }


        // =====================================================
        // MEZZI
        // =====================================================

        if (mezzoRepository.count() == 0) {

            Mezzo mezzo1 = new Mezzo();
            mezzo1.setTarga("DY190XE");
            mezzo1.setModello("MAN");
            mezzo1.setTipologia("2 Assi Scarrabile");
            mezzo1.setAttivo(true);

            Mezzo mezzo2 = new Mezzo();
            mezzo2.setTarga("FX470SE");
            mezzo2.setModello("Mercedes");
            mezzo2.setTipologia("4 Assi Scarrabile");
            mezzo2.setAttivo(true);

            Mezzo mezzo3 = new Mezzo();
            mezzo3.setTarga("FS366CW");
            mezzo3.setModello("DAF");
            mezzo3.setTipologia("3 Assi Scarrabile");
            mezzo3.setAttivo(true);

            Mezzo mezzo4 = new Mezzo();
            mezzo4.setTarga("GD512FY");
            mezzo4.setModello("DAF");
            mezzo4.setTipologia("2 Assi Scarrabile");
            mezzo4.setAttivo(true);

            Mezzo mezzo5 = new Mezzo();
            mezzo5.setTarga("GF297EW");
            mezzo5.setModello("Iveco");
            mezzo5.setTipologia("4 Assi Scarrabile");
            mezzo5.setAttivo(true);

            Mezzo mezzo6 = new Mezzo();
            mezzo6.setTarga("HC222HV");
            mezzo6.setModello("Iveco");
            mezzo6.setTipologia("4 Assi Scarrabile");
            mezzo6.setAttivo(true);

            Mezzo mezzo7 = new Mezzo();
            mezzo7.setTarga("HC223HV");
            mezzo7.setModello("Iveco");
            mezzo7.setTipologia("3 Assi");
            mezzo7.setAttivo(true);

            Mezzo mezzo8 = new Mezzo();
            mezzo8.setTarga("ER399GW");
            mezzo8.setModello("Iveco");
            mezzo8.setTipologia("Ribaltabile con gru");
            mezzo8.setAttivo(true);

            Mezzo mezzo9 = new Mezzo();
            mezzo9.setTarga("HC133HV");
            mezzo9.setModello("Iveco");
            mezzo9.setTipologia("Furgone Scarrabile");
            mezzo9.setAttivo(true);


            mezzoRepository.save(mezzo1);
            mezzoRepository.save(mezzo2);
            mezzoRepository.save(mezzo3);
            mezzoRepository.save(mezzo4);
            mezzoRepository.save(mezzo5);
            mezzoRepository.save(mezzo6);
            mezzoRepository.save(mezzo7);
            mezzoRepository.save(mezzo8);
            mezzoRepository.save(mezzo9);

            System.out.println("Mezzi iniziali creati.");
        }




        // =====================================================
        // UTENTI
        // =====================================================

        if (utenteRepository.count() == 0) {

            Mezzo mezzo1 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("DY190XE")
                    )
                    .findFirst()
                    .orElse(null);

            Mezzo mezzo2 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("FX470SE")
                    )
                    .findFirst()
                    .orElse(null);

            Mezzo mezzo3 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("FS366CW")
                    )
                    .findFirst()
                    .orElse(null);

            Mezzo mezzo4 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("GD512FY")
                    )
                    .findFirst()
                    .orElse(null);

            Mezzo mezzo5 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("GF297EW")
                    )
                    .findFirst()
                    .orElse(null);

            Mezzo mezzo6 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("HC222HV")
                    )
                    .findFirst()
                    .orElse(null);

            Mezzo mezzo7 = mezzoRepository
                    .findAll()
                    .stream()
                    .filter(mezzo ->
                            mezzo.getTarga().equalsIgnoreCase("HC223HV")
                    )
                    .findFirst()
                    .orElse(null);


            // =================================================
            // ADMIN
            // =================================================

            Utente admin = new Utente();

            admin.setNome("Admin");
            admin.setCognome("Sistema");
            admin.setNickname("Admin");

            admin.setPassword(
                    passwordEncoder.encode("Califfo68!")
            );

            admin.setRuolo(Ruolo.ADMIN);
            admin.setAttivo(true);
            admin.setMezzo(null);


            // =================================================
            // OPERAIO 1
            // =================================================

            Utente operaio1 = new Utente();

            operaio1.setNome("Valentino");
            operaio1.setCognome("Babuta");
            operaio1.setNickname("Valentino Babuta");

            operaio1.setPassword(
                    passwordEncoder.encode("Valentino.1974")
            );

            operaio1.setRuolo(Ruolo.OPERAIO);
            operaio1.setAttivo(true);
            operaio1.setMezzo(mezzo1);


            // =================================================
            // OPERAIO 2
            // =================================================

            Utente operaio2 = new Utente();

            operaio2.setNome("Jhonny");
            operaio2.setCognome("Contofanti");
            operaio2.setNickname("Jhonny Contofanti");

            operaio2.setPassword(
                    passwordEncoder.encode("Jhonny.1978")
            );

            operaio2.setRuolo(Ruolo.OPERAIO);
            operaio2.setAttivo(true);
            operaio2.setMezzo(mezzo2);


            // =================================================
            // OPERAIO 3
            // =================================================

            Utente operaio3 = new Utente();

            operaio3.setNome("Simone");
            operaio3.setCognome("Bucci");
            operaio3.setNickname("Simone Bucci");

            operaio3.setPassword(
                    passwordEncoder.encode("Simone.1971")
            );

            operaio3.setRuolo(Ruolo.OPERAIO);
            operaio3.setAttivo(true);
            operaio3.setMezzo(mezzo3);


            // =================================================
            // OPERAIO 4
            // =================================================

            Utente operaio4 = new Utente();

            operaio4.setNome("Daniel");
            operaio4.setCognome("Spac");
            operaio4.setNickname("Daniel Spac");

            operaio4.setPassword(
                    passwordEncoder.encode("Daniel.1980")
            );

            operaio4.setRuolo(Ruolo.OPERAIO);
            operaio4.setAttivo(true);
            operaio4.setMezzo(mezzo4);


            // =================================================
            // OPERAIO 5
            // =================================================

            Utente operaio5 = new Utente();

            operaio5.setNome("Marco");
            operaio5.setCognome("De Castris");
            operaio5.setNickname("Marco De Castris");

            operaio5.setPassword(
                    passwordEncoder.encode("Marco.1978")
            );

            operaio5.setRuolo(Ruolo.OPERAIO);
            operaio5.setAttivo(true);
            operaio5.setMezzo(mezzo5);


            // =================================================
            // OPERAIO 6
            // =================================================

            Utente operaio6 = new Utente();

            operaio6.setNome("Luigi");
            operaio6.setCognome("Masella");
            operaio6.setNickname("Luigi Masella");

            operaio6.setPassword(
                    passwordEncoder.encode("Luigi.1975")
            );

            operaio6.setRuolo(Ruolo.OPERAIO);
            operaio6.setAttivo(true);
            operaio6.setMezzo(mezzo6);


            // =================================================
            // OPERAIO 7
            // =================================================

            Utente operaio7 = new Utente();

            operaio7.setNome("Angelo");
            operaio7.setCognome("Calafato");
            operaio7.setNickname("Angelo Calafato");

            operaio7.setPassword(
                    passwordEncoder.encode("Angelo.1968")
            );

            operaio7.setRuolo(Ruolo.OPERAIO);
            operaio7.setAttivo(true);
            operaio7.setMezzo(mezzo7);


            // =================================================
            // OPERAIO 8
            // =================================================

            Utente operaio8 = new Utente();

            operaio8.setNome("Emiliano");
            operaio8.setCognome("Cerci");
            operaio8.setNickname("Emiliano Cerci");

            operaio8.setPassword(
                    passwordEncoder.encode("Emiliano.1977")
            );

            operaio8.setRuolo(Ruolo.OPERAIO);
            operaio8.setAttivo(true);
            operaio8.setMezzo(null);


            // =================================================
            // OPERAIO 9
            // =================================================

            Utente operaio9 = new Utente();

            operaio9.setNome("Giuseppe");
            operaio9.setCognome("Calafato");
            operaio9.setNickname("Giuseppe Calafato");

            operaio9.setPassword(
                    passwordEncoder.encode("Giuseppe.1999")
            );

            operaio9.setRuolo(Ruolo.OPERAIO);
            operaio9.setAttivo(true);
            operaio9.setMezzo(null);


            // =================================================
            // OPERAIO 10
            // =================================================

            Utente operaio10 = new Utente();

            operaio10.setNome("Daniele");
            operaio10.setCognome("Alvedi");
            operaio10.setNickname("Daniele Alvedi");

            operaio10.setPassword(
                    passwordEncoder.encode("Daniele.1978")
            );

            operaio10.setRuolo(Ruolo.OPERAIO);
            operaio10.setAttivo(true);
            operaio10.setMezzo(null);


            // =================================================
            // OPERAIO 11
            // =================================================

            Utente operaio11 = new Utente();

            operaio11.setNome("Giuseppe");
            operaio11.setCognome("Masella");
            operaio11.setNickname("Giuseppe Masella");

            operaio11.setPassword(
                    passwordEncoder.encode("Giuseppe.1975")
            );

            operaio11.setRuolo(Ruolo.OPERAIO);
            operaio11.setAttivo(true);
            operaio11.setMezzo(null);


            // =================================================
            // OPERAIO 12
            // =================================================

            Utente operaio12 = new Utente();

            operaio12.setNome("Alessandro");
            operaio12.setCognome("Boccuccia");
            operaio12.setNickname("Alessandro Boccuccia");

            operaio12.setPassword(
                    passwordEncoder.encode("Alessandro.1983")
            );

            operaio12.setRuolo(Ruolo.OPERAIO);
            operaio12.setAttivo(true);
            operaio12.setMezzo(null);


            // =================================================
            // SALVATAGGIO UTENTI
            // =================================================

            utenteRepository.save(admin);

            utenteRepository.save(operaio1);
            utenteRepository.save(operaio2);
            utenteRepository.save(operaio3);
            utenteRepository.save(operaio4);
            utenteRepository.save(operaio5);
            utenteRepository.save(operaio6);
            utenteRepository.save(operaio7);
            utenteRepository.save(operaio8);
            utenteRepository.save(operaio9);
            utenteRepository.save(operaio10);
            utenteRepository.save(operaio11);
            utenteRepository.save(operaio12);

            System.out.println("Admin e 12 operai creati.");
        }




        // =====================================================
        // CASSONI
        // =====================================================

        if (cassoneRepository.count() == 0) {

            Luogo piazzale = luogoRepository
                    .findAll()
                    .stream()
                    .filter(luogo ->
                            luogo.getNome().equalsIgnoreCase(
                                    "PIAZZALE"
                            )
                    )
                    .findFirst()
                    .orElse(null);


            Utente operaio3 = utenteRepository
                    .findAll()
                    .stream()
                    .filter(utente ->
                            utente.getNickname().equalsIgnoreCase(
                                    "Simone Bucci"
                            )
                    )
                    .findFirst()
                    .orElse(null);


            Utente operaio1 = utenteRepository
                    .findAll()
                    .stream()
                    .filter(utente ->
                            utente.getNickname().equalsIgnoreCase(
                                    "Valentino Babuta"
                            )
                    )
                    .findFirst()
                    .orElse(null);

            Cassone cassone1 = new Cassone();
            cassone1.setCodiceCassone("1");
            cassone1.setColore("ALTO BIANCO");
            cassone1.setMisura(null);
            cassone1.setAttivo(false);
            cassone1.setPosizioneIniziale(piazzale);
            cassone1.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone2 = new Cassone();
            cassone2.setCodiceCassone("2");
            cassone2.setColore("ALTO BLU");
            cassone2.setMisura("3 assi");
            cassone2.setAttivo(true);
            cassone2.setPosizioneIniziale(piazzale);
            cassone2.setDataPosizioneIniziale(LocalDateTime.of(2026,8,27,8,0));

            Cassone cassone3 = new Cassone();
            cassone3.setCodiceCassone("3");
            cassone3.setColore("BASSO BLU");
            cassone3.setMisura("3 assi");
            cassone3.setAttivo(true);
            cassone3.setPosizioneIniziale(luogo4);
            cassone3.setDataPosizioneIniziale(LocalDateTime.of(2025, 6, 9, 8,0));

            Cassone cassone4 = new Cassone();
            cassone4.setCodiceCassone("4");
            cassone4.setColore("ALTO BLU");
            cassone4.setMisura("3 assi");
            cassone4.setAttivo(true);
            cassone4.setPosizioneIniziale(luogo31);
            cassone4.setDataPosizioneIniziale(LocalDateTime.of(2026, 7, 2, 8,0));

            Cassone cassone5 = new Cassone();
            cassone5.setCodiceCassone("5");
            cassone5.setColore("BASSO BLU");
            cassone5.setMisura("3 assi");
            cassone5.setAttivo(true);
            cassone5.setPosizioneIniziale(piazzale);
            cassone5.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone6 = new Cassone();
            cassone6.setCodiceCassone("6");
            cassone6.setColore("BASSO BIANCO");
            cassone6.setMisura("3 assi");
            cassone6.setAttivo(true);
            cassone6.setPosizioneIniziale(luogo9);
            cassone6.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 15, 8,0));

            Cassone cassone7 = new Cassone();
            cassone7.setCodiceCassone("7-A");
            cassone7.setColore("BASSO BIANCO");
            cassone7.setMisura("3 assi");
            cassone7.setAttivo(true);
            cassone7.setPosizioneIniziale(piazzale);
            cassone7.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone8 = new Cassone();
            cassone8.setCodiceCassone("7-B");
            cassone8.setColore("BASSO BLU");
            cassone8.setMisura("3 assi");
            cassone8.setAttivo(true);
            cassone8.setPosizioneIniziale(luogo16);
            cassone8.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 16, 8,0));

            Cassone cassone9 = new Cassone();
            cassone9.setCodiceCassone("8");
            cassone9.setColore("BASSO BLU");
            cassone9.setMisura("3 assi");
            cassone9.setAttivo(true);
            cassone9.setPosizioneIniziale(luogo53);
            cassone9.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone10 = new Cassone();

            cassone10.setCodiceCassone("9");
            cassone10.setColore("BASSO BLU");
            cassone10.setMisura("3 assi");
            cassone10.setAttivo(true);
            cassone10.setPosizioneIniziale(piazzale);
            cassone10.setDataPosizioneIniziale(LocalDateTime.now());
            cassone10.setBloccato(true);
            cassone10.setBloccatoDa(operaio3);
            cassone10.setMotivoBlocco("PIENO - DA RIPRENDERE");

            Cassone cassone11 = new Cassone();
            cassone11.setCodiceCassone("10");
            cassone11.setColore("BASSO BLU");
            cassone11.setMisura("3 assi");
            cassone11.setAttivo(true);
            cassone11.setPosizioneIniziale(luogo32);
            cassone11.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone12 = new Cassone();
            cassone12.setCodiceCassone("11");
            cassone12.setColore("ALTO BLU");
            cassone12.setMisura("3 assi");
            cassone12.setAttivo(true);
            cassone12.setPosizioneIniziale(luogo35);
            cassone12.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 25, 8,0));

            Cassone cassone13 = new Cassone();
            cassone13.setCodiceCassone("12");
            cassone13.setColore("BASSO BLU");
            cassone13.setMisura("3 assi");
            cassone13.setAttivo(true);
            cassone13.setPosizioneIniziale(luogo4);
            cassone13.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 13, 8,0));

            Cassone cassone14 = new Cassone();
            cassone14.setCodiceCassone("14");
            cassone14.setColore("BASSO BLU");
            cassone14.setMisura("3 assi");
            cassone14.setAttivo(true);
            cassone14.setPosizioneIniziale(luogo25);
            cassone14.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 3, 8,0));

            Cassone cassone15 = new Cassone();
            cassone15.setCodiceCassone("15");
            cassone15.setColore("BASSO BLU");
            cassone15.setMisura("3 assi");
            cassone15.setAttivo(true);
            cassone15.setPosizioneIniziale(luogo26);
            cassone15.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 25, 8,0));

            Cassone cassone16 = new Cassone();
            cassone16.setCodiceCassone("16");
            cassone16.setColore("BASSO BLU");
            cassone16.setMisura("3 assi");
            cassone16.setAttivo(true);
            cassone16.setPosizioneIniziale(luogo32);
            cassone16.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone17 = new Cassone();
            cassone17.setCodiceCassone("17");
            cassone17.setColore("BASSO BLU");
            cassone17.setMisura("3 assi");
            cassone17.setAttivo(true);
            cassone17.setPosizioneIniziale(luogo5);
            cassone17.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 9, 8,0));

            Cassone cassone18 = new Cassone();
            cassone18.setCodiceCassone("18");
            cassone18.setColore("BASSO BLU");
            cassone18.setMisura("3 assi");
            cassone18.setAttivo(true);
            cassone18.setPosizioneIniziale(luogo34);
            cassone18.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 25, 8,0));

            Cassone cassone19 = new Cassone();
            cassone19.setCodiceCassone("19");
            cassone19.setColore("BASSO BLU");
            cassone19.setMisura("3 assi");
            cassone19.setAttivo(true);
            cassone19.setPosizioneIniziale(piazzale);
            cassone19.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 12, 8,0));

            Cassone cassone20 = new Cassone();
            cassone20.setCodiceCassone("20");
            cassone20.setColore("BASSO BLU");
            cassone20.setMisura("3 assi");
            cassone20.setAttivo(true);
            cassone20.setPosizioneIniziale(luogo54);
            cassone20.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone21 = new Cassone();
            cassone21.setCodiceCassone("21");
            cassone21.setColore("BASSO BLU");
            cassone21.setMisura("3 assi");
            cassone21.setAttivo(true);
            cassone21.setPosizioneIniziale(luogo34);
            cassone21.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone22 = new Cassone();
            cassone22.setCodiceCassone("27");
            cassone22.setColore("BASSO BLU (20 MC)");
            cassone22.setMisura("4 assi");
            cassone22.setAttivo(true);
            cassone22.setPosizioneIniziale(luogo16);
            cassone22.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 16, 8,0));

            Cassone cassone23 = new Cassone();
            cassone23.setCodiceCassone("23");
            cassone23.setColore("ALTO BLU (28 MC)");
            cassone23.setMisura("3 assi");
            cassone23.setAttivo(true);
            cassone23.setPosizioneIniziale(luogo55);
            cassone23.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone24 = new Cassone();
            cassone24.setCodiceCassone("24");
            cassone24.setColore("BASSO BLU");
            cassone24.setMisura("3 assi");
            cassone24.setAttivo(true);
            cassone24.setPosizioneIniziale(luogo30);
            cassone24.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 12, 8,0));

            Cassone cassone25 = new Cassone();
            cassone25.setCodiceCassone("25");
            cassone25.setColore("BASSO BLU");
            cassone25.setMisura("3 assi");
            cassone25.setAttivo(true);
            cassone25.setPosizioneIniziale(luogo40);
            cassone25.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 8, 8,0));

            Cassone cassone26 = new Cassone();
            cassone26.setCodiceCassone("34");
            cassone26.setColore("BASSO BLU");
            cassone26.setMisura("3 assi");
            cassone26.setAttivo(true);
            cassone26.setPosizioneIniziale(luogo11);
            cassone26.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 9, 8,0));

            Cassone cassone27 = new Cassone();
            cassone27.setCodiceCassone("35");
            cassone27.setColore("ALTO BLU");
            cassone27.setMisura("3 assi");
            cassone27.setAttivo(true);
            cassone27.setPosizioneIniziale(luogo12);
            cassone27.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 29, 8,0));

            Cassone cassone28 = new Cassone();
            cassone28.setCodiceCassone("36");
            cassone28.setColore("BASSO BLU");
            cassone28.setMisura("3 assi");
            cassone28.setAttivo(true);
            cassone28.setPosizioneIniziale(luogo7);
            cassone28.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 2, 8,0));

            Cassone cassone29 = new Cassone();
            cassone29.setCodiceCassone("37");
            cassone29.setColore("20 MC BLU");
            cassone29.setMisura("4 assi");
            cassone29.setAttivo(true);
            cassone29.setPosizioneIniziale(piazzale);
            cassone29.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone30 = new Cassone();
            cassone30.setCodiceCassone("44");
            cassone30.setColore("20 MC BLU CON TELO");
            cassone30.setMisura("4 assi");
            cassone30.setAttivo(true);
            cassone30.setPosizioneIniziale(luogo16);
            cassone30.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 16, 8,0));

            Cassone cassone31 = new Cassone();
            cassone31.setCodiceCassone("45");
            cassone31.setColore("20 MC BLU");
            cassone31.setMisura("4 assi");
            cassone31.setAttivo(true);
            cassone31.setPosizioneIniziale(luogo38);
            cassone31.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 9, 8,0));

            Cassone cassone32 = new Cassone();
            cassone32.setCodiceCassone("46");
            cassone32.setColore("20 MC BLU CON TELO");
            cassone32.setMisura("4 assi");
            cassone32.setAttivo(true);
            cassone32.setPosizioneIniziale(piazzale);
            cassone32.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 24, 8,0));

            Cassone cassone33 = new Cassone();
            cassone33.setCodiceCassone("47");
            cassone33.setColore("20 MC ARANCIONE");
            cassone33.setMisura("4 assi");
            cassone33.setAttivo(true);
            cassone33.setPosizioneIniziale(piazzale);
            cassone33.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone34 = new Cassone();
            cassone34.setCodiceCassone("48");
            cassone34.setColore("20 MC ARANCIONE");
            cassone34.setMisura("4 assi");
            cassone34.setAttivo(true);
            cassone34.setPosizioneIniziale(luogo39);
            cassone34.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 14, 8,0));

            Cassone cassone35 = new Cassone();
            cassone35.setCodiceCassone("49");
            cassone35.setColore("20 MC ARANCIONE");
            cassone35.setMisura("4 assi");
            cassone35.setAttivo(true);
            cassone35.setPosizioneIniziale(luogo56);
            cassone35.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone36 = new Cassone();
            cassone36.setCodiceCassone("50");
            cassone36.setColore("20 MC ARANCIONE TELO BIANCO");
            cassone36.setMisura("4 assi");
            cassone36.setAttivo(true);
            cassone36.setPosizioneIniziale(luogo40);
            cassone36.setDataPosizioneIniziale(LocalDateTime.of(2026, 8, 13, 8,0));

            Cassone cassone37 = new Cassone();
            cassone37.setCodiceCassone("59");
            cassone37.setColore("BLU SENZA RINFORZO");
            cassone37.setMisura("4 assi");
            cassone37.setAttivo(true);
            cassone37.setPosizioneIniziale(piazzale);
            cassone37.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone38 = new Cassone();
            cassone38.setCodiceCassone("60");
            cassone38.setColore("BASSO BLU");
            cassone38.setMisura("3 assi");
            cassone38.setAttivo(true);
            cassone38.setPosizioneIniziale(luogo6);
            cassone38.setDataPosizioneIniziale(LocalDateTime.of(2026, 7, 8, 8,0));

            Cassone cassone39 = new Cassone();
            cassone39.setCodiceCassone("61");
            cassone39.setColore("BASSO BLU");
            cassone39.setMisura("3 assi");
            cassone39.setAttivo(true);
            cassone39.setPosizioneIniziale(piazzale);
            cassone39.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 18, 8,0));

            Cassone cassone40 = new Cassone();
            cassone40.setCodiceCassone("62");
            cassone40.setColore("BASSO BLU");
            cassone40.setMisura("3 assi");
            cassone40.setAttivo(true);
            cassone40.setPosizioneIniziale(luogo27);
            cassone40.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 15, 8,0));

            Cassone cassone41 = new Cassone();
            cassone41.setCodiceCassone("63");
            cassone41.setColore("BASSO BLU");
            cassone41.setMisura("3 assi");
            cassone41.setAttivo(true);
            cassone41.setPosizioneIniziale(luogo18);
            cassone41.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 18, 8,0));

            Cassone cassone42 = new Cassone();
            cassone42.setCodiceCassone("64-GRANDE");
            cassone42.setColore("20 MC BLU");
            cassone42.setMisura("4 assi");
            cassone42.setAttivo(true);
            cassone42.setPosizioneIniziale(luogo4);
            cassone42.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone43 = new Cassone();
            cassone43.setCodiceCassone("65");
            cassone43.setColore("20 MC BLU");
            cassone43.setMisura("4 assi");
            cassone43.setAttivo(true);
            cassone43.setPosizioneIniziale(luogo14);
            cassone43.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 2, 8,0));

            Cassone cassone44 = new Cassone();
            cassone44.setCodiceCassone("66");
            cassone44.setColore("ALTO BLU (28 MC)");
            cassone44.setMisura("3 assi");
            cassone44.setAttivo(true);
            cassone44.setPosizioneIniziale(luogo4);
            cassone44.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone45 = new Cassone();
            cassone45.setCodiceCassone("67");
            cassone45.setColore("ALTO BLU (28 MC)");
            cassone45.setMisura("3 assi");
            cassone45.setAttivo(true);
            cassone45.setPosizioneIniziale(piazzale);
            cassone45.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 8, 8,0));

            Cassone cassone46 = new Cassone();
            cassone46.setCodiceCassone("F&F1");
            cassone46.setColore("NERO F&F");
            cassone46.setMisura("2 assi");
            cassone46.setAttivo(true);
            cassone46.setPosizioneIniziale(luogo49);
            cassone46.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 2, 8,0));

            Cassone cassone47 = new Cassone();
            cassone47.setCodiceCassone("F&F2");
            cassone47.setColore("NERO F&F");
            cassone47.setMisura("2 assi");
            cassone47.setAttivo(true);
            cassone47.setPosizioneIniziale(piazzale);
            cassone47.setDataPosizioneIniziale(LocalDateTime.now());
            cassone47.setBloccato(true);
            cassone47.setBloccatoDa(operaio1);
            cassone47.setMotivoBlocco("PIENO - DA RIPRENDERE");

            Cassone cassone48 = new Cassone();
            cassone48.setCodiceCassone("13");
            cassone48.setColore("BLU");
            cassone48.setMisura("2 assi");
            cassone48.setAttivo(true);
            cassone48.setPosizioneIniziale(luogo16);
            cassone48.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 17, 8,0));

            Cassone cassone49 = new Cassone();
            cassone49.setCodiceCassone("22");
            cassone49.setColore("BLU");
            cassone49.setMisura("2 assi");
            cassone49.setAttivo(true);
            cassone49.setPosizioneIniziale(luogo1);
            cassone49.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 1, 8,0));

            Cassone cassone50 = new Cassone();
            cassone50.setCodiceCassone("26");
            cassone50.setColore("BLU");
            cassone50.setMisura("2 assi");
            cassone50.setAttivo(false);
            cassone50.setPosizioneIniziale(piazzale);
            cassone50.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone51 = new Cassone();
            cassone51.setCodiceCassone("28");
            cassone51.setColore("ALTO BLU");
            cassone51.setMisura("2 assi");
            cassone51.setAttivo(true);
            cassone51.setPosizioneIniziale(luogo57);
            cassone51.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 15, 8,0));

            Cassone cassone52 = new Cassone();
            cassone52.setCodiceCassone("29");
            cassone52.setColore("BLU");
            cassone52.setMisura("2 assi");
            cassone52.setAttivo(true);
            cassone52.setPosizioneIniziale(luogo7);
            cassone52.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 18, 8,0));

            Cassone cassone53 = new Cassone();
            cassone53.setCodiceCassone("30");
            cassone53.setColore("BLU");
            cassone53.setMisura("2 assi");
            cassone53.setAttivo(true);
            cassone53.setPosizioneIniziale(luogo45);
            cassone53.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 8, 8,0));

            Cassone cassone54 = new Cassone();
            cassone54.setCodiceCassone("31");
            cassone54.setColore("BLU");
            cassone54.setMisura("2 assi");
            cassone54.setAttivo(true);
            cassone54.setPosizioneIniziale(piazzale);
            cassone54.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 17, 8,0));

            Cassone cassone55 = new Cassone();
            cassone55.setCodiceCassone("32");
            cassone55.setColore("BLU SPONDA ALLUMINIO");
            cassone55.setMisura("2 assi");
            cassone55.setAttivo(true);
            cassone55.setPosizioneIniziale(luogo8);
            cassone55.setDataPosizioneIniziale(LocalDateTime.of(2026, 6, 16, 8,0));

            Cassone cassone56 = new Cassone();
            cassone56.setCodiceCassone("33");
            cassone56.setColore("BLU");
            cassone56.setMisura("2 assi");
            cassone56.setAttivo(true);
            cassone56.setPosizioneIniziale(luogo7);
            cassone56.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 14, 8,0));

            Cassone cassone57 = new Cassone();
            cassone57.setCodiceCassone("38");
            cassone57.setColore("BLU");
            cassone57.setMisura("2 assi");
            cassone57.setAttivo(true);
            cassone57.setPosizioneIniziale(luogo41);
            cassone57.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 15, 8,0));

            Cassone cassone58 = new Cassone();
            cassone58.setCodiceCassone("39");
            cassone58.setColore("BLU");
            cassone58.setMisura("2 assi");
            cassone58.setAttivo(true);
            cassone58.setPosizioneIniziale(luogo36);
            cassone58.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 21, 8,0));

            Cassone cassone59 = new Cassone();
            cassone59.setCodiceCassone("40");
            cassone59.setColore("BLU");
            cassone59.setMisura("2 assi");
            cassone59.setAttivo(false);
            cassone59.setPosizioneIniziale(piazzale);
            cassone59.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone60 = new Cassone();
            cassone60.setCodiceCassone("41");
            cassone60.setColore("BLU");
            cassone60.setMisura("2 assi");
            cassone60.setAttivo(true);
            cassone60.setPosizioneIniziale(luogo58);
            cassone60.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 13, 8,0));

            Cassone cassone61 = new Cassone();
            cassone61.setCodiceCassone("42");
            cassone61.setColore("BLU");
            cassone61.setMisura("2 assi");
            cassone61.setAttivo(true);
            cassone61.setPosizioneIniziale(piazzale);
            cassone61.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 13, 8,0));

            Cassone cassone62 = new Cassone();
            cassone62.setCodiceCassone("43");
            cassone62.setColore("BLU");
            cassone62.setMisura("2 assi");
            cassone62.setAttivo(true);
            cassone62.setPosizioneIniziale(luogo10);
            cassone62.setDataPosizioneIniziale(LocalDateTime.of(2026, 4, 24, 8,0));

            Cassone cassone63 = new Cassone();
            cassone63.setCodiceCassone("51");
            cassone63.setColore("ARANCIONE");
            cassone63.setMisura("2 assi");
            cassone63.setAttivo(true);
            cassone63.setPosizioneIniziale(luogo2);
            cassone63.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 24, 8,0));

            Cassone cassone64 = new Cassone();
            cassone64.setCodiceCassone("52");
            cassone64.setColore("ARANCIONE");
            cassone64.setMisura("2 assi");
            cassone64.setAttivo(true);
            cassone64.setPosizioneIniziale(luogo3);
            cassone64.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 30, 8,0));

            Cassone cassone65 = new Cassone();
            cassone65.setCodiceCassone("53");
            cassone65.setColore("ARANCIONE");
            cassone65.setMisura("2 assi");
            cassone65.setAttivo(true);
            cassone65.setPosizioneIniziale(luogo48);
            cassone65.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone66 = new Cassone();
            cassone66.setCodiceCassone("54");
            cassone66.setColore("ARANCIONE");
            cassone66.setMisura("2 assi");
            cassone66.setAttivo(true);
            cassone66.setPosizioneIniziale(luogo23);
            cassone66.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 8, 8,0));

            Cassone cassone67 = new Cassone();
            cassone67.setCodiceCassone("55");
            cassone67.setColore("ARANCIONE");
            cassone67.setMisura("2 assi");
            cassone67.setAttivo(true);
            cassone67.setPosizioneIniziale(luogo33);
            cassone67.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 9, 8,0));

            Cassone cassone68 = new Cassone();
            cassone68.setCodiceCassone("56");
            cassone68.setColore("BLU");
            cassone68.setMisura("2 assi");
            cassone68.setAttivo(true);
            cassone68.setPosizioneIniziale(luogo50);
            cassone68.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 15, 8,0));

            Cassone cassone69 = new Cassone();
            cassone69.setCodiceCassone("57");
            cassone69.setColore("BLU");
            cassone69.setMisura("2 assi");
            cassone69.setAttivo(true);
            cassone69.setPosizioneIniziale(luogo24);
            cassone69.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone70 = new Cassone();
            cassone70.setCodiceCassone("58");
            cassone70.setColore("BLU");
            cassone70.setMisura("2 assi");
            cassone70.setAttivo(true);
            cassone70.setPosizioneIniziale(luogo44);
            cassone70.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 17, 8,0));

            Cassone cassone71 = new Cassone();
            cassone71.setCodiceCassone("ROSSO1");
            cassone71.setColore("ROSSO");
            cassone71.setMisura("2 assi");
            cassone71.setAttivo(true);
            cassone71.setPosizioneIniziale(luogo42);
            cassone71.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 7, 8,0));

            Cassone cassone72 = new Cassone();
            cassone72.setCodiceCassone("ROSSO2");
            cassone72.setColore("ROSSO");
            cassone72.setMisura("2 assi");
            cassone72.setAttivo(true);
            cassone72.setPosizioneIniziale(luogo47);
            cassone72.setDataPosizioneIniziale(LocalDateTime.of(2026, 9, 27, 8,0));

            Cassone cassone73 = new Cassone();
            cassone73.setCodiceCassone("ROSSO3");
            cassone73.setColore("ROSSO");
            cassone73.setMisura("2 assi");
            cassone73.setAttivo(false);
            cassone73.setPosizioneIniziale(piazzale);
            cassone73.setDataPosizioneIniziale(LocalDateTime.now());

            Cassone cassone74 = new Cassone();
            cassone74.setCodiceCassone("64-PICCOLO");
            cassone74.setColore("BLU");
            cassone74.setMisura("2 assi");
            cassone74.setAttivo(true);
            cassone74.setPosizioneIniziale(luogo59);
            cassone74.setDataPosizioneIniziale(LocalDateTime.of(2026, 10, 13, 8,0));



            cassoneRepository.saveAll(
                    List.of(
                            cassone1, cassone2, cassone3, cassone4, cassone5,
                            cassone6, cassone7, cassone8, cassone9, cassone10,

                            cassone11, cassone12, cassone13, cassone14, cassone15,
                            cassone16, cassone17, cassone18, cassone19, cassone20,

                            cassone21, cassone22, cassone23, cassone24, cassone25,
                            cassone26, cassone27, cassone28, cassone29, cassone30,

                            cassone31, cassone32, cassone33, cassone34, cassone35,
                            cassone36, cassone37, cassone38, cassone39, cassone40,

                            cassone41, cassone42, cassone43, cassone44, cassone45,
                            cassone46, cassone47, cassone48, cassone49, cassone50,

                            cassone51, cassone52, cassone53, cassone54, cassone55,
                            cassone56, cassone57, cassone58, cassone59, cassone60,

                            cassone61, cassone62, cassone63, cassone64, cassone65,
                            cassone66, cassone67, cassone68, cassone69, cassone70,

                            cassone71, cassone72, cassone73, cassone74
                    )
            );

            System.out.println("Cassoni iniziali creati.");
        }


        System.out.println("DataInitializer completato.");
    }
}
