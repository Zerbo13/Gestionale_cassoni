package mattiazerbini.gestionale_cassoni.services;

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

        viaggio.setDataOraInizio(LocalDateTime.now());
        viaggio.setDataOraFine(null);
        viaggio.setStato(StatoViaggio.IN_CORSO);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio chiudiViaggio(Long id) {

        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

        if (viaggio.getStato() != StatoViaggio.IN_CORSO) {
            throw new RuntimeException("Il viaggio non è in corso");
        }

        viaggio.setDataOraFine(LocalDateTime.now());
        viaggio.setStato(StatoViaggio.COMPLETATO);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio annullaViaggio(Long id) {

        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaggio non trovato"));

        viaggio.setStato(StatoViaggio.ANNULLATO);

        return viaggioRepository.save(viaggio);
    }
}