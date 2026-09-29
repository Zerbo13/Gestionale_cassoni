package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.StatoViaggio;
import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import mattiazerbini.gestionale_cassoni.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

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

    public Optional<Viaggio> trovaViaggioPerId(Long id) {
        return viaggioRepository.findById(id);
    }

    public Viaggio avviaViaggio(Viaggio viaggio) {

        Cassone cassone = viaggio.getCassone();

        // Controllo se il cassone ha già un viaggio in corso
        Optional<Viaggio> viaggioInCorso =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraInizioDesc(
                                cassone.getId(),
                                StatoViaggio.IN_CORSO
                        );

        if (viaggioInCorso.isPresent()) {
            throw new RuntimeException(
                    "Il cassone ha già un viaggio in corso"
            );
        }

        // Cerco l'ultimo viaggio completato del cassone
        Optional<Viaggio> ultimoViaggioCompletato =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(
                                cassone.getId(),
                                StatoViaggio.COMPLETATO
                        );

        // Se esiste, la nuova partenza è l'ultima destinazione
        if (ultimoViaggioCompletato.isPresent()) {

            viaggio.setPartenza(
                    ultimoViaggioCompletato
                            .get()
                            .getDestinazione()
            );

        } else {

            // Se non ha mai viaggiato usa la posizione iniziale
            viaggio.setPartenza(
                    cassone.getPosizioneIniziale()
            );
        }

        viaggio.setDataOraInizio(LocalDateTime.now());
        viaggio.setDataOraFine(null);
        viaggio.setStato(StatoViaggio.IN_CORSO);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio chiudiViaggio(Long id) {

        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Viaggio non trovato")
                );

        if (viaggio.getStato() != StatoViaggio.IN_CORSO) {
            throw new RuntimeException(
                    "Il viaggio non è in corso"
            );
        }

        viaggio.setDataOraFine(LocalDateTime.now());
        viaggio.setStato(StatoViaggio.COMPLETATO);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio annullaViaggio(Long id) {

        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Viaggio non trovato")
                );

        viaggio.setStato(StatoViaggio.ANNULLATO);

        return viaggioRepository.save(viaggio);
    }

    public String trovaPosizioneAttualeCassone(Cassone cassone) {

        Optional<Viaggio> viaggioInCorso =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraInizioDesc(
                                cassone.getId(),
                                StatoViaggio.IN_CORSO
                        );

        if (viaggioInCorso.isPresent()) {
            return "In viaggio verso " +
                    viaggioInCorso.get().getDestinazione().getNome();
        }

        Optional<Viaggio> ultimoViaggioCompletato =
                viaggioRepository
                        .findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(
                                cassone.getId(),
                                StatoViaggio.COMPLETATO
                        );

        if (ultimoViaggioCompletato.isPresent()) {
            return ultimoViaggioCompletato
                    .get()
                    .getDestinazione()
                    .getNome();
        }

        if (cassone.getPosizioneIniziale() != null) {
            return cassone.getPosizioneIniziale().getNome();
        }

        return "Posizione non disponibile";
    }
}