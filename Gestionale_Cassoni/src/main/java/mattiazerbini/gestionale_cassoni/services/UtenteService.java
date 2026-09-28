package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.repositories.UtenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public List<Utente> trovaTuttiUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> trovaUtentePerId(Long id) {
        return utenteRepository.findById(id);
    }

    public Utente salvaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }
}
