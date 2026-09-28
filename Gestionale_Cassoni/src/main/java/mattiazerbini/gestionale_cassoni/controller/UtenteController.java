package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.services.UtenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utenti")
@CrossOrigin(origins = "*")
public class UtenteController {

    private final UtenteService utenteService;

    public UtenteController(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @GetMapping
    public List<Utente> getTuttiGliUtenti() {
        return utenteService.trovaTuttiGliUtenti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtentePerId(@PathVariable Long id) {
        return utenteService.trovaUtentePerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Utente creaUtente(@RequestBody Utente utente) {
        return utenteService.salvaUtente(utente);
    }

    @PutMapping("/{id}")
    public Utente modificaUtente(
            @PathVariable Long id,
            @RequestBody Utente utente
    ) {
        return utenteService.modificaUtente(id, utente);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Void> disattivaUtente(@PathVariable Long id) {

        utenteService.disattivaUtente(id);

        return ResponseEntity.noContent().build();
    }
}