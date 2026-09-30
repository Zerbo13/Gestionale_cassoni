package mattiazerbini.gestionale_cassoni.services;

import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.repositories.UtenteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
        this.utenteRepository = utenteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utente> trovaTuttiGliUtenti() {
        return utenteRepository.findAll();
    }

    public Optional<Utente> trovaUtentePerId(Long id) {
        return utenteRepository.findById(id);
    }

    public Utente salvaUtente(Utente utente) {
        utente.setPassword(passwordEncoder.encode(utente.getPassword()));

        return utenteRepository.save(utente);
    }

    public Utente modificaUtente(Long id, Utente utenteModificato) {

        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        utente.setNome(utenteModificato.getNome());
        utente.setCognome(utenteModificato.getCognome());
        utente.setNickname(utenteModificato.getNickname());
        utente.setRuolo(utenteModificato.getRuolo());
        utente.setMezzo(utenteModificato.getMezzo());

        return utenteRepository.save(utente);
    }

    public Utente disattivaUtente(Long id) {

        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        utente.setAttivo(false);

        return utenteRepository.save(utente);
    }

    public Utente attivaUtente(Long id) {

        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        utente.setAttivo(true);

        return utenteRepository.save(utente);
    }

    public Utente trovaPerNickname(String nickname) {

        return utenteRepository
                .findByNicknameIgnoreCase(nickname)
                .orElseThrow(() -> new NotFoundException("Credenziali non valide"));
    }

    public List<Utente> trovaUtentiAttivi() {
        return utenteRepository.findByAttivoTrue();
    }
}