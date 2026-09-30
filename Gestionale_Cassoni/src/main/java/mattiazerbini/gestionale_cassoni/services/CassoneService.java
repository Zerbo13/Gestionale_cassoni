package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.repositories.CassoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CassoneService {

    private final CassoneRepository cassoneRepository;

    public CassoneService(CassoneRepository cassoneRepository) {
        this.cassoneRepository = cassoneRepository;
    }

    public List<Cassone> trovaTuttiICassoni() {
        return cassoneRepository.findAll();
    }

    public Optional<Cassone> trovaCassonePerId(Long id) {
        return cassoneRepository.findById(id);
    }

    public Cassone salvaCassone(Cassone cassone) {
        return cassoneRepository.save(cassone);
    }

    public Cassone modificaCassone(Long id, Cassone cassoneModificato) {

        Cassone cassone = cassoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cassone non trovato"));

        cassone.setCodiceCassone(cassoneModificato.getCodiceCassone());
        cassone.setColore(cassoneModificato.getColore());
        cassone.setMisura(cassoneModificato.getMisura());
        cassone.setTipologia(cassoneModificato.getTipologia());
        cassone.setCapacità(cassoneModificato.getCapacità());
        cassone.setPosizioneIniziale(cassoneModificato.getPosizioneIniziale());
        return cassoneRepository.save(cassone);
    }

    public Cassone disattivaCassone(Long id) {

        Cassone cassone = cassoneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cassone non trovato"));

        cassone.setAttivo(false);

        return cassoneRepository.save(cassone);
    }
}