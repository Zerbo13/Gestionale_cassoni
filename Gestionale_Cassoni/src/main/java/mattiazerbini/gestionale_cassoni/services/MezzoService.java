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
}