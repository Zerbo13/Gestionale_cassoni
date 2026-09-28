package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.repositories.MezzoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MezzoService {

    private final MezzoRepository mezzoRepository;

    public MezzoService(MezzoRepository mezzoRepository) {
        this.mezzoRepository = mezzoRepository;
    }

    public List<Mezzo> trovaTuttiIMezzi() {
        return mezzoRepository.findAll();
    }

    public Optional<Mezzo> trovaMezzoPerId(Long id) {
        return mezzoRepository.findById(id);
    }

    public Mezzo salvaMezzo(Mezzo mezzo) {
        return mezzoRepository.save(mezzo);
    }

    public Mezzo modificaMezzo(Long id, Mezzo mezzoModificato) {
        Mezzo mezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mezzo non trovato"));

        mezzo.setTarga(mezzoModificato.getTarga());
        mezzo.setModello(mezzoModificato.getModello());
        mezzo.setTipologia(mezzoModificato.getTipologia());

        return mezzoRepository.save(mezzo);
    }

    public void disattivaMezzo(Long id) {
        Mezzo mezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mezzo non trovato"));

        mezzo.setAttivo(false);
        mezzoRepository.save(mezzo);
    }
}