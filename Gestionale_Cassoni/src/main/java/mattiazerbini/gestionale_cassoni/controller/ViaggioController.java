package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.dto.ViaggioRequest;
import mattiazerbini.gestionale_cassoni.entities.*;
import mattiazerbini.gestionale_cassoni.services.CassoneService;
import mattiazerbini.gestionale_cassoni.services.LuogoService;
import mattiazerbini.gestionale_cassoni.services.ViaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@CrossOrigin(origins = "*")
public class ViaggioController {

    private final ViaggioService viaggioService;
    private final CassoneService cassoneService;
    private final LuogoService luogoService;

    public ViaggioController(ViaggioService viaggioService, CassoneService cassoneService, LuogoService luogoService) {
        this.viaggioService = viaggioService;
        this.cassoneService = cassoneService;
        this.luogoService = luogoService;
    }

    @GetMapping
    public List<Viaggio> getTuttiIViaggi() {
        return viaggioService.trovaTuttiIViaggi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioPerId(@PathVariable Long id) {
        return viaggioService.trovaViaggioPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/avvia")
    public Viaggio avviaViaggio(@RequestBody ViaggioRequest request, Authentication authentication) {
        Utente utente = (Utente) authentication.getPrincipal();
        Mezzo mezzo = utente.getMezzo();

        if (mezzo == null){
            throw new RuntimeException("Nessun mezzo assegnato all'utente!");
        }

        Cassone cassone = cassoneService.trovaCassonePerId(request.getCassoneId())
                .orElseThrow(() -> new RuntimeException("Cassone non trovato"));

        Luogo destinazione = luogoService.trovaLuogoPerId(request.getDestinazioneId())
                .orElseThrow(() -> new RuntimeException("Destinazione non trovato"));

        Viaggio viaggio = new Viaggio();

        viaggio.setUtente(utente);
        viaggio.setMezzo(mezzo);
        viaggio.setCassone(cassone);
        viaggio.setDestinazione(destinazione);
        viaggio.setNote(request.getNote());

        return viaggioService.avviaViaggio(viaggio);
    }

    @PutMapping("/{id}/chiudi")
    public Viaggio chiudiViaggio(@PathVariable Long id) {
        return viaggioService.chiudiViaggio(id);
    }

    @PutMapping("/{id}/annulla")
    public Viaggio annullaViaggio(@PathVariable Long id) {
        return viaggioService.annullaViaggio(id);
    }
}