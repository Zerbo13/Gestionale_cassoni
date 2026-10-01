package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.exceptions.ConflictException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
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

        if (mezzoRepository.existsByTargaIgnoreCase(mezzo.getTarga())) {
            throw new ConflictException("Targa già in uso su un altro mezzo!");
        }
        return mezzoRepository.save(mezzo);
    }

    public Mezzo modificaMezzo(Long id, Mezzo mezzoModificato) {

        Mezzo mezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

        if (mezzoRepository.existsByTargaIgnoreCaseAndIdNot(mezzo.getTarga(), id)) {
            throw new ConflictException("Targa già in utilizzo su un altro mezzo!!");
        }

        mezzo.setTarga(mezzoModificato.getTarga());
        mezzo.setModello(mezzoModificato.getModello());
        mezzo.setTipologia(mezzoModificato.getTipologia());

        return mezzoRepository.save(mezzo);
    }

    public Mezzo disattivaMezzo(Long id) {

        Mezzo mezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

        mezzo.setAttivo(false);

        return mezzoRepository.save(mezzo);
    }

    public Mezzo attivaMezzo(Long id) {

        Mezzo mezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

        mezzo.setAttivo(true);

        return mezzoRepository.save(mezzo);
    }

    public List<Mezzo> trovaMezziAttivi() {
        return mezzoRepository.findByAttivoTrue();
    }
}