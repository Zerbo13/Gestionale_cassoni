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

    public List<Utente> trovaTuttiGliUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> trovaUtentePerId(Long id) {
        return utenteRepository.findById(id);
    }

    public Utente salvaUtente(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Utente modificaUtente(Long id, Utente utenteModificato) {

        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        utente.setNome(utenteModificato.getNome());
        utente.setCognome(utenteModificato.getCognome());
        utente.setNickname(utenteModificato.getNickname());
        utente.setRuolo(utenteModificato.getRuolo());
        utente.setMezzo(utenteModificato.getMezzo());

        return utenteRepository.save(utente);
    }

    public void disattivaUtente(Long id) {

        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        utente.setAttivo(false);

        utenteRepository.save(utente);
    }

    public Utente trovaPerNickname(String nickname) {
        return utenteRepository.findByNickname(nickname)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
    }
}