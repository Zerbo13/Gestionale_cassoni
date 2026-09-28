package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import mattiazerbini.gestionale_cassoni.repositories.ViaggioRepository;
import org.springframework.stereotype.Service;

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

    public Viaggio salvaViaggio(Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }
}