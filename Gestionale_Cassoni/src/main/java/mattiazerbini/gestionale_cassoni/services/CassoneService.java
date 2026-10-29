package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.StatoViaggio;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.ConflictException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import mattiazerbini.gestionale_cassoni.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CassoneService {

    private final CassoneRepository cassoneRepository;
    private final ViaggioRepository viaggioRepository;

    public CassoneService(
            CassoneRepository cassoneRepository,
            ViaggioRepository viaggioRepository
    ) {
        this.cassoneRepository = cassoneRepository;
        this.viaggioRepository = viaggioRepository;
    }

    // Restituisce tutti i cassoni
    public List<Cassone> trovaTuttiICassoni() {
        return cassoneRepository.findAll();
    }

    // Cerca un cassone per ID
    public Optional<Cassone> trovaCassonePerId(Long id) {
        return cassoneRepository.findById(id);
    }

    // Salva un nuovo cassone
    public Cassone salvaCassone(Cassone cassone) {

        if (cassoneRepository.existsByCodiceCassoneIgnoreCase(
                cassone.getCodiceCassone()
        )) {
            throw new ConflictException(
                    "Codice cassone già in utilizzo!"
            );
        }

        return cassoneRepository.save(cassone);
    }

    // Modifica un cassone esistente
    public Cassone modificaCassone(
            Long id,
            Cassone cassoneModificato
    ) {
        Cassone cassone = cassoneRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Cassone non trovato")
                );

        // Controlliamo il nuovo codice, non quello precedente
        if (cassoneRepository
                .existsByCodiceCassoneIgnoreCaseAndIdNot(
                        cassoneModificato.getCodiceCassone(),
                        id
                )) {
            throw new ConflictException(
                    "Codice cassone già in utilizzo!"
            );
        }

        cassone.setCodiceCassone(
                cassoneModificato.getCodiceCassone()
        );

        cassone.setColore(
                cassoneModificato.getColore()
        );

        cassone.setMisura(
                cassoneModificato.getMisura()
        );

        // Conserviamo i campi facoltativi se non sono stati inviati
        if (cassoneModificato.getTipologia() != null) {
            cassone.setTipologia(
                    cassoneModificato.getTipologia()
            );
        }

        if (cassoneModificato.getCapacità() != null) {
            cassone.setCapacità(
                    cassoneModificato.getCapacità()
            );
        }

        // Conserviamo la posizione esistente se non ne è stata
        // indicata una nuova
        if (cassoneModificato.getPosizioneIniziale() != null) {
            cassone.setPosizioneIniziale(
                    cassoneModificato.getPosizioneIniziale()
            );

            cassone.setDataPosizioneIniziale(
                    cassoneModificato.getDataPosizioneIniziale()
            );
        }

        // Non modifichiamo lo stato attivo durante l'aggiornamento
        return cassoneRepository.save(cassone);
    }

    // Attiva un cassone
    public Cassone attivaCassone(Long id) {

        Cassone cassone = cassoneRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Cassone non trovato")
                );

        cassone.setAttivo(true);

        return cassoneRepository.save(cassone);
    }

    // Disattiva un cassone
    public Cassone disattivaCassone(Long id) {

        Cassone cassone = cassoneRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Cassone non trovato")
                );

        // Non è possibile disattivare un cassone con un viaggio in corso
        if (viaggioRepository.existsByCassoneIdAndStato(
                id,
                StatoViaggio.IN_CORSO
        )) {
            throw new BadRequestException(
                    "Non puoi disattivare un cassone con un viaggio in corso"
            );
        }

        cassone.setAttivo(false);

        return cassoneRepository.save(cassone);
    }
}