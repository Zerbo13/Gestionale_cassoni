package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.dto.UtenteRequest;
import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.entities.Ruolo;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.services.MezzoService;
import mattiazerbini.gestionale_cassoni.services.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "*")
public class UtenteController {

    private final UtenteService utenteService;
    private final MezzoService mezzoService;

    public UtenteController(
            UtenteService utenteService,
            MezzoService mezzoService
    ) {
        this.utenteService = utenteService;
        this.mezzoService = mezzoService;
    }

    @GetMapping
    public List<Utente> getTuttiGliUtenti() {
        return utenteService.trovaTuttiGliUtenti();
    }

    @GetMapping("/attivi")
    public List<Utente> getUtentiAttivi() {
        return utenteService.trovaUtentiAttivi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtentePerId(@PathVariable Long id) {

        Utente utente = utenteService
                .trovaUtentePerId(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        return ResponseEntity.ok(utente);
    }

    @PostMapping
    public Utente creaUtente(@RequestBody UtenteRequest request) {

        Utente utente = new Utente();
        utente.setNome(request.getNome());
        utente.setCognome(request.getCognome());
        utente.setNickname(request.getNickname());
        utente.setPassword(request.getPassword());
        utente.setRuolo(Ruolo.valueOf(request.getRuolo().toUpperCase()));

        if (request.getMezzoId() != null) {

            Mezzo mezzo = mezzoService
                    .trovaMezzoPerId(request.getMezzoId())
                    .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

            utente.setMezzo(mezzo);
        }

        utente.setAttivo(true);

        return utenteService.salvaUtente(utente);
    }

    @PutMapping("/{id}")
    public Utente modificaUtente(@PathVariable Long id, @RequestBody UtenteRequest request) {

        Utente utente = utenteService
                .trovaUtentePerId(id)
                .orElseThrow(() -> new NotFoundException("Utente non trovato"));

        utente.setNome(request.getNome());
        utente.setCognome(request.getCognome());
        utente.setNickname(request.getNickname());
        utente.setRuolo(Ruolo.valueOf(request.getRuolo().toUpperCase()));

        if (request.getMezzoId() != null) {
            Mezzo mezzo = mezzoService
                    .trovaMezzoPerId(request.getMezzoId())
                    .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

            utente.setMezzo(mezzo);
        } else {
            utente.setMezzo(null);
        }

        return utenteService.modificaUtente(id, utente);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Utente> disattivaUtente(@PathVariable Long id) {

        Utente utente = utenteService.disattivaUtente(id);

        return ResponseEntity.ok(utente);
    }

    @PutMapping("/{id}/attiva")
    public ResponseEntity<Utente> attivaUtente(@PathVariable Long id) {

        Utente utente = utenteService.attivaUtente(id);

        return ResponseEntity.ok(utente);
    }
}