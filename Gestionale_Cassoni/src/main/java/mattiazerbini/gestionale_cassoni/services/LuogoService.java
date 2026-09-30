package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
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

    public Luogo modificaLuogo(Long id, Luogo luogoModificato) {

        Luogo luogo = luogoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Luogo non trovato"));
        luogo.setNome(luogoModificato.getNome());
        luogo.setIndirizzo(luogoModificato.getIndirizzo());
        luogo.setTipologia(luogoModificato.getTipologia());

        return luogoRepository.save(luogo);
    }

    public Luogo disattivaLuogo(Long id) {

        Luogo luogo = luogoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Luogo non trovato"));

        luogo.setAttivo(false);

        return luogoRepository.save(luogo);
    }

    public Luogo attivaLuogo(Long id) {

        Luogo luogo = luogoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Luogo non trovato"));

        luogo.setAttivo(true);

        return luogoRepository.save(luogo);
    }

    public List<Luogo> trovaLuoghiAttivi() {
        return luogoRepository.findByAttivoTrue();
    }
}