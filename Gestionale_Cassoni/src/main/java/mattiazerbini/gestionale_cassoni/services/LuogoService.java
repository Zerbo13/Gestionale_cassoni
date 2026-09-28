package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.repositories.LuogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LuogoService {

    private final LuogoRepository luogoRepository;

    public LuogoService(LuogoRepository luogoRepository) {
        this.luogoRepository = luogoRepository;
    }

    public List<Luogo> trovaTuttiILuoghi() {
        return luogoRepository.findAll();
    }

    public Optional<Luogo> trovaLuogoPerId(Long id) {
        return luogoRepository.findById(id);
    }

    public Luogo salvaLuogo(Luogo luogo) {
        return luogoRepository.save(luogo);
    }
}