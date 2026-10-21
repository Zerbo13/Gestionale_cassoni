package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.dto.PosizioneCassoneResponse;
import mattiazerbini.gestionale_cassoni.dto.ViaggioResponse;
import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.StatoViaggio;
import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import mattiazerbini.gestionale_cassoni.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;
    private final CassoneRepository cassoneRepository;

    private final ZoneId zonaItalia = ZoneId.of("Europe/Rome");


    public ViaggioService(
            ViaggioRepository viaggioRepository,
            CassoneRepository cassoneRepository
    ) {
        this.viaggioRepository = viaggioRepository;
        this.cassoneRepository = cassoneRepository;
    }


    public List<Viaggio> trovaTuttiIViaggi() {

        return viaggioRepository.findAll();
    }


    public List<Viaggio> trovaViaggiPerCassone(Long cassoneId) {

        return viaggioRepository
                .findByCassoneIdOrderByDataOraInizioDesc(cassoneId);
    }


    public List<Viaggio> trovaViaggiPerUtente(Long utenteId) {

        return viaggioRepository
                .findByUtenteIdOrderByDataOraInizioDesc(utenteId);
    }


    public Optional<Viaggio> trovaViaggioPerId(Long id) {

        return viaggioRepository.findById(id);
    }


    public Viaggio salvaViaggio(Viaggio viaggio) {

        return viaggioRepository.save(viaggio);
    }


    public List<Viaggio> trovaViaggiDiOggi() {

        LocalDate oggi =
                LocalDate.now(zonaItalia);

        LocalDateTime dataInizio =
                oggi.atStartOfDay();

        LocalDateTime dataFine =
                oggi
                        .plusDays(1)
                        .atStartOfDay();

        return viaggioRepository
                .findByDataOraInizioBetweenOrderByDataOraInizioDesc(
                        dataInizio,
                        dataFine
                );
    }


    public ViaggioResponse convetiInResponse(Viaggio viaggio) {

        return new ViaggioResponse(
                viaggio.getId(),

                viaggio.getUtente().getNome()
                        + " "
                        + viaggio.getUtente().getCognome(),

                viaggio.getMezzo().getTarga()
                        + " "
                        + viaggio.getMezzo().getModello(),

                viaggio.getCassone().getCodiceCassone(),

                viaggio.getPartenza().getNome(),

                viaggio.getDestinazione().getNome()
                        + (
                        viaggio.getDestinazione().getIndirizzo() != null
                                && !viaggio.getDestinazione().getIndirizzo().isBlank()
                                ? " - " + viaggio.getDestinazione().getIndirizzo()
                                : ""
                )
                        + (
                        viaggio.getDestinazione().getTipologia() != null
                                && !viaggio.getDestinazione().getTipologia().isBlank()
                                ? " - " + viaggio.getDestinazione().getTipologia()
                                : ""
                ),

                viaggio.getDataOraInizio(),

                viaggio.getDataOraFine(),

                viaggio.getStato(),

                viaggio.getNote(),

                viaggio.getFotoUrl()
        );
    }


    public Viaggio avviaViaggio(Viaggio viaggio) {

        Cassone cassone = viaggio.getCassone();


        // =====================================================
        // CONTROLLO CASSONE GIÀ IN VIAGGIO
        // =====================================================

        boolean cassoneOccupato =
                viaggioRepository.existsByCassoneIdAndStato(
                        cassone.getId(),
                        StatoViaggio.IN_CORSO
                );

        if (cassoneOccupato) {

            throw new BadRequestException(
                    "Il cassone è già impegnato in un viaggio"
            );
        }


        // =====================================================
        // CONTROLLO CASSONE BLOCCATO / RISERVATO
        // =====================================================

        if (Boolean.TRUE.equals(cassone.getBloccato())) {

            if (cassone.getBloccatoDa() == null) {

                throw new BadRequestException(
                        "Questo cassone è momentaneamente bloccato. Contatta l'amministratore."
                );
            }


            if (
                    !cassone
                            .getBloccatoDa()
                            .getId()
                            .equals(
                                    viaggio
                                            .getUtente()
                                            .getId()
                            )
            ) {

                throw new BadRequestException(
                        "Questo cassone è riservato a "
                                + cassone.getBloccatoDa().getNome()
                                + " "
                                + cassone.getBloccatoDa().getCognome()
                );
            }
        }


        // =====================================================
        // CONTROLLO MEZZO
        // =====================================================

        boolean mezzoOccupato =
                viaggioRepository.existsByMezzoIdAndStato(
                        viaggio.getMezzo().getId(),
                        StatoViaggio.IN_CORSO
                );

        if (mezzoOccupato) {

            throw new BadRequestException(
                    "Il mezzo è già impegnato in un viaggio"
            );
        }


        // =====================================================
        // CONTROLLO AUTISTA
        // =====================================================

        boolean autistaOccupato =
                viaggioRepository.existsByUtenteIdAndStato(
                        viaggio.getUtente().getId(),
                        StatoViaggio.IN_CORSO
                );

        if (autistaOccupato) {

            throw new BadRequestException(
                    "Hai già un viaggio in corso"
            );
        }


        // =====================================================
        // CALCOLO PARTENZA CASSONE
        // =====================================================

        Optional<Viaggio> ultimoViaggioCompletato =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(
                                cassone.getId(),
                                StatoViaggio.COMPLETATO
                        );


        if (ultimoViaggioCompletato.isPresent()) {

            viaggio.setPartenza(
                    ultimoViaggioCompletato
                            .get()
                            .getDestinazione()
            );

        } else {

            if (
                    cassone.getPosizioneIniziale() == null
            ) {

                throw new BadRequestException(
                        "Il cassone non ha una posizione iniziale"
                );
            }


            viaggio.setPartenza(
                    cassone.getPosizioneIniziale()
            );
        }


        viaggio.setDataOraInizio(
                LocalDateTime.now(zonaItalia)
        );


        viaggio.setStato(
                StatoViaggio.IN_CORSO
        );


        return viaggioRepository.save(viaggio);
    }


    public Viaggio chiudiViaggio(Long id) {

        Viaggio viaggio =
                viaggioRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Viaggio non trovato"
                                )
                        );


        if (
                viaggio.getStato()
                        != StatoViaggio.IN_CORSO
        ) {

            throw new BadRequestException(
                    "Puoi chiudere solo un viaggio in corso"
            );
        }


        viaggio.setDataOraFine(
                LocalDateTime.now(zonaItalia)
        );


        viaggio.setStato(
                StatoViaggio.COMPLETATO
        );


        // =====================================================
        // SBLOCCO AUTOMATICO DEL CASSONE
        // =====================================================

        Cassone cassone =
                viaggio.getCassone();


        if (
                Boolean.TRUE.equals(cassone.getBloccato())
                        &&
                        cassone.getBloccatoDa() != null
                        &&
                        cassone
                                .getBloccatoDa()
                                .getId()
                                .equals(
                                        viaggio
                                                .getUtente()
                                                .getId()
                                )
        ) {

            cassone.setBloccato(false);

            cassone.setBloccatoDa(null);

            cassone.setMotivoBlocco(null);

            cassoneRepository.save(cassone);
        }


        return viaggioRepository.save(viaggio);
    }


    public Viaggio annullaViaggio(Long id) {

        Viaggio viaggio =
                viaggioRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Viaggio non trovato"
                                )
                        );


        if (
                viaggio.getStato()
                        != StatoViaggio.IN_CORSO
        ) {

            throw new BadRequestException(
                    "Puoi annullare solo un viaggio in corso"
            );
        }


        viaggio.setStato(
                StatoViaggio.ANNULLATO
        );


        /*
         * IMPORTANTE:
         *
         * Se il viaggio viene ANNULLATO il cassone NON viene sbloccato.
         *
         * Quindi continuerà ad essere riservato allo stesso operaio.
         */

        return viaggioRepository.save(viaggio);
    }


    // =====================================================
    // ANNULLA VIAGGIO COMPLETATO - ADMIN
    // =====================================================

    public Viaggio annullaViaggioCompletatoAdmin(Long id) {

        Viaggio viaggio =
                viaggioRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(
                                        "Viaggio non trovato"
                                )
                        );


        if (
                viaggio.getStato()
                        != StatoViaggio.COMPLETATO
        ) {

            throw new BadRequestException(
                    "Puoi annullare solo un viaggio completato"
            );
        }


        viaggio.setStato(
                StatoViaggio.ANNULLATO
        );


        return viaggioRepository.save(viaggio);
    }


    private long calcolaGiorniFermo(
            LocalDateTime dataArrivo
    ) {

        LocalDate dataInizio =
                dataArrivo.toLocalDate();

        LocalDate oggi =
                LocalDate.now(zonaItalia);

        long giorni = 0;

        LocalDate data =
                dataInizio.plusDays(1);


        while (!data.isAfter(oggi)) {

            if (
                    data.getDayOfWeek()
                            != DayOfWeek.SUNDAY
            ) {

                giorni++;
            }

            data = data.plusDays(1);
        }


        return giorni;
    }


    public PosizioneCassoneResponse trovaPosizioneAttualeCassone(
            Cassone cassone
    ) {

        Optional<Viaggio> viaggioInCorso =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraInizioDesc(
                                cassone.getId(),
                                StatoViaggio.IN_CORSO
                        );


        if (viaggioInCorso.isPresent()) {

            Viaggio viaggio =
                    viaggioInCorso.get();


            String destinazione =
                    viaggio
                            .getDestinazione()
                            .getNome()
                            + (
                            viaggio.getDestinazione().getIndirizzo() != null
                                    && !viaggio.getDestinazione().getIndirizzo().isBlank()
                                    ? " - " + viaggio.getDestinazione().getIndirizzo()
                                    : ""
                    );


            return new PosizioneCassoneResponse(
                    cassone.getCodiceCassone(),

                    "In viaggio verso " + destinazione,

                    0,

                    viaggio.getNote()
            );
        }


        Optional<Viaggio> ultimoViaggioCompletato =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(
                                cassone.getId(),
                                StatoViaggio.COMPLETATO
                        );


        if (ultimoViaggioCompletato.isPresent()) {

            Viaggio ultimoViaggio =
                    ultimoViaggioCompletato.get();


            long giorniFermo =
                    calcolaGiorniFermo(
                            ultimoViaggio
                                    .getDataOraFine()
                    );


            String posizione =
                    ultimoViaggio
                            .getDestinazione()
                            .getNome()
                            + (
                            ultimoViaggio.getDestinazione().getIndirizzo() != null
                                    && !ultimoViaggio.getDestinazione().getIndirizzo().isBlank()
                                    ? " - " + ultimoViaggio.getDestinazione().getIndirizzo()
                                    : ""
                    );


            return new PosizioneCassoneResponse(
                    cassone.getCodiceCassone(),

                    posizione,

                    giorniFermo,

                    ultimoViaggio.getNote()
            );
        }


        if (
                cassone.getPosizioneIniziale() != null
                        &&
                        cassone.getDataPosizioneIniziale() != null
        ) {

            long giorniFermo =
                    calcolaGiorniFermo(
                            cassone
                                    .getDataPosizioneIniziale()
                    );


            String posizioneIniziale =
                    cassone
                            .getPosizioneIniziale()
                            .getNome()
                            + (
                            cassone.getPosizioneIniziale().getIndirizzo() != null
                                    && !cassone.getPosizioneIniziale().getIndirizzo().isBlank()
                                    ? " - " + cassone.getPosizioneIniziale().getIndirizzo()
                                    : ""
                    );


            return new PosizioneCassoneResponse(
                    cassone.getCodiceCassone(),

                    posizioneIniziale,

                    giorniFermo,

                    null
            );
        }


        if (
                cassone.getPosizioneIniziale() != null
        ) {

            String posizioneIniziale =
                    cassone
                            .getPosizioneIniziale()
                            .getNome()
                            + (
                            cassone.getPosizioneIniziale().getIndirizzo() != null
                                    && !cassone.getPosizioneIniziale().getIndirizzo().isBlank()
                                    ? " - " + cassone.getPosizioneIniziale().getIndirizzo()
                                    : ""
                    );


            return new PosizioneCassoneResponse(
                    cassone.getCodiceCassone(),

                    posizioneIniziale,

                    0,

                    null
            );
        }


        return new PosizioneCassoneResponse(
                cassone.getCodiceCassone(),
                "Posizione non disponibile",
                0,
                null
        );
    }

}
