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

    public CassoneService(CassoneRepository cassoneRepository, ViaggioRepository viaggioRepository) {
        this.cassoneRepository = cassoneRepository;
        this.viaggioRepository = viaggioRepository;
    }

    public List<Cassone> trovaTuttiICassoni() {
        return cassoneRepository.findAll();
    }

    public Optional<Cassone> trovaCassonePerId(Long id) {
        return cassoneRepository.findById(id);
    }

    public Cassone salvaCassone(Cassone cassone) {

        if (cassoneRepository.existsByCodiceCassoneIgnoreCase(cassone.getCodiceCassone())) {
            throw new ConflictException("Codice cassone già in utilizzo!");
        }
        return cassoneRepository.save(cassone);
    }

    public Cassone modificaCassone(Long id, Cassone cassoneModificato) {

        Cassone cassone = cassoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cassone non trovato"));

        if (cassoneRepository.existsByCodiceCassoneIgnoreCaseAndIdNot(cassone.getCodiceCassone(), id)) {
            throw new ConflictException("Codice cassone già in utilizzo!");
        }

        cassone.setCodiceCassone(cassoneModificato.getCodiceCassone());
        cassone.setColore(cassoneModificato.getColore());
        cassone.setMisura(cassoneModificato.getMisura());
        cassone.setTipologia(cassoneModificato.getTipologia());
        cassone.setCapacità(cassoneModificato.getCapacità());
        cassone.setPosizioneIniziale(cassoneModificato.getPosizioneIniziale());

        return cassoneRepository.save(cassone);
    }

    public Cassone disattivaCassone(Long id) {

        Cassone cassone = cassoneRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Cassone non trovato"));

        if (viaggioRepository.existsByCassoneIdAndStato(id, StatoViaggio.IN_CORSO)) {
            throw new BadRequestException("Non puoi disattivare un cassone con un viaggio in corso");
        }
        cassone.setAttivo(false);
        return cassoneRepository.save(cassone);
    }
}