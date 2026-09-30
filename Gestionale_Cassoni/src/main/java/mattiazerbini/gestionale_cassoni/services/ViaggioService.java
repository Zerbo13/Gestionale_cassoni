package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.dto.ViaggioResponse;
import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.StatoViaggio;
import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public List<Viaggio> trovaTuttiIViaggi() {
        return viaggioRepository.findAll();
    }

    public List<Viaggio> trovaViaggiPerCassone(Long cassoneId) {
        return viaggioRepository.findByCassoneIdOrderByDataOraInizioDesc(cassoneId);
    }

    public List<Viaggio> trovaViaggiPerUtente(Long utenteId) {
        return viaggioRepository.findByUtenteIdOrderByDataOraInizioDesc(utenteId);
    }

    public Optional<Viaggio> trovaViaggioPerId(Long id) {
        return viaggioRepository.findById(id);
    }

    public List<Viaggio> trovaViaggiDiOggi() {

        LocalDateTime dataInizio = LocalDate.now().atStartOfDay();
        LocalDateTime dataFine = LocalDate.now()
                .plusDays(1)
                .atStartOfDay();

        return viaggioRepository
                .findByDataOraInizioBetweenOrderByDataOraInizioDesc(dataInizio, dataFine);
    }

    public ViaggioResponse convetiInResponse(Viaggio viaggio) {

        return new ViaggioResponse(viaggio.getId(),
                viaggio.getUtente().getNome()
                        + " "
                        + viaggio.getUtente().getCognome(),

                viaggio.getMezzo().getTarga()
                        + " "
                        + viaggio.getMezzo().getModello(),

                viaggio.getCassone().getCodiceCassone(),
                viaggio.getPartenza().getNome(),
                viaggio.getDestinazione().getNome(),
                viaggio.getDataOraInizio(),
                viaggio.getDataOraFine(),
                viaggio.getStato(),
                viaggio.getNote()
        );
    }

    public Viaggio avviaViaggio(Viaggio viaggio) {

        Cassone cassone = viaggio.getCassone();

        Optional<Viaggio> viaggioInCorso =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraInizioDesc(cassone.getId(),
                                StatoViaggio.IN_CORSO);

        if (viaggioInCorso.isPresent()) {
            throw new BadRequestException("Il cassone ha già un viaggio in corso");
        }

        Optional<Viaggio> ultimoViaggioCompletato =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(cassone.getId(),
                                StatoViaggio.COMPLETATO);

        if (ultimoViaggioCompletato.isPresent()) {

            viaggio.setPartenza(ultimoViaggioCompletato
                            .get()
                            .getDestinazione());

        } else {

            if (cassone.getPosizioneIniziale() == null) {
                throw new BadRequestException("Il cassone non ha una posizione iniziale");
            }

            viaggio.setPartenza(cassone.getPosizioneIniziale());
        }

        viaggio.setDataOraInizio(LocalDateTime.now());

        viaggio.setDataOraFine(null);

        viaggio.setStato(StatoViaggio.IN_CORSO);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio chiudiViaggio(Long id) {

        Viaggio viaggio = viaggioRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        if (viaggio.getStato() != StatoViaggio.IN_CORSO) {

            throw new BadRequestException("Puoi chiudere solo un viaggio in corso");
        }

        viaggio.setDataOraFine(LocalDateTime.now());

        viaggio.setStato(StatoViaggio.COMPLETATO);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio annullaViaggio(Long id) {

        Viaggio viaggio = viaggioRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato")
                );

        if (viaggio.getStato() != StatoViaggio.IN_CORSO) {

            throw new BadRequestException("Puoi annullare solo un viaggio in corso");
        }

        viaggio.setStato(
                StatoViaggio.ANNULLATO
        );

        return viaggioRepository.save(viaggio);
    }

    public String trovaPosizioneAttualeCassone(
            Cassone cassone
    ) {

        Optional<Viaggio> viaggioInCorso =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraInizioDesc(cassone.getId(),
                                StatoViaggio.IN_CORSO);

        if (viaggioInCorso.isPresent()) {

            return "In viaggio verso "
                    + viaggioInCorso
                    .get()
                    .getDestinazione()
                    .getNome();
        }

        Optional<Viaggio> ultimoViaggioCompletato =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(cassone.getId(),
                                StatoViaggio.COMPLETATO);

        if (ultimoViaggioCompletato.isPresent()) {

            return ultimoViaggioCompletato
                    .get()
                    .getDestinazione()
                    .getNome();
        }

        if (cassone.getPosizioneIniziale() != null) {

            return cassone
                    .getPosizioneIniziale()
                    .getNome();
        }

        return "Posizione non disponibile";
    }
}