package mattiazerbini.gestionale_cassoni.controller;

import jakarta.validation.Valid;
import mattiazerbini.gestionale_cassoni.dto.ViaggioRequest;
import mattiazerbini.gestionale_cassoni.dto.ViaggioResponse;
import mattiazerbini.gestionale_cassoni.entities.*;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.ForbiddenException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
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

    public ViaggioController(
            ViaggioService viaggioService,
            CassoneService cassoneService,
            LuogoService luogoService
    ) {
        this.viaggioService = viaggioService;
        this.cassoneService = cassoneService;
        this.luogoService = luogoService;
    }

    @GetMapping
    public List<ViaggioResponse> getTuttiIViaggi() {

        return viaggioService.trovaTuttiIViaggi()
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioPerId(@PathVariable Long id) {

        Viaggio viaggio = viaggioService
                .trovaViaggioPerId(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        return ResponseEntity.ok(viaggio);
    }

    @PostMapping("/avvia")
    public ViaggioResponse avviaViaggio(@Valid @RequestBody ViaggioRequest request, Authentication authentication) {

        Utente utente = (Utente) authentication.getPrincipal();

        Mezzo mezzo = utente.getMezzo();

        if (mezzo == null) {
            throw new BadRequestException("Nessun mezzo assegnato all'utente");
        }

        Cassone cassone = cassoneService
                .trovaCassonePerId(request.getCassoneId())
                .orElseThrow(() -> new NotFoundException("Cassone non trovato"));

        Luogo destinazione = luogoService
                .trovaLuogoPerId(request.getDestinazioneId())
                .orElseThrow(() -> new NotFoundException("Destinazione non trovata"));

        Viaggio viaggio = new Viaggio();

        viaggio.setUtente(utente);
        viaggio.setMezzo(mezzo);
        viaggio.setCassone(cassone);
        viaggio.setDestinazione(destinazione);
        viaggio.setNote(request.getNote());

        Viaggio viaggioSalvato = viaggioService.avviaViaggio(viaggio);

        return viaggioService.convetiInResponse(viaggioSalvato);
    }

    @PutMapping("/{id}/chiudi")
    public ViaggioResponse chiudiViaggio(@PathVariable Long id, Authentication authentication) {

        Utente utente = (Utente) authentication.getPrincipal();

        Viaggio viaggio = viaggioService
                .trovaViaggioPerId(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        if (utente.getRuolo() != Ruolo.ADMIN && !viaggio.getUtente().getId().equals(utente.getId())) {
            throw new ForbiddenException("Non puoi chiudere il viaggio di un altro operaio");
        }

        Viaggio viaggioChiuso = viaggioService.chiudiViaggio(id);

        return viaggioService.convetiInResponse(viaggioChiuso);
    }

    @PutMapping("/{id}/annulla")
    public ViaggioResponse annullaViaggio(@PathVariable Long id, Authentication authentication) {

        Utente utente = (Utente) authentication.getPrincipal();

        Viaggio viaggio = viaggioService.trovaViaggioPerId(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        if (utente.getRuolo() != Ruolo.ADMIN && !viaggio.getUtente().getId().equals(utente.getId())) {
            throw new ForbiddenException("Non puoi annullare il viaggio di un altro operaio");
        }

        Viaggio viaggioAnnullato = viaggioService.annullaViaggio(id);

        return viaggioService.convetiInResponse(viaggioAnnullato);
    }

    @GetMapping("/cassone/{cassoneId}")
    public List<ViaggioResponse> getViaggiPerCassone(@PathVariable Long cassoneId) {

        return viaggioService
                .trovaViaggiPerCassone(cassoneId)
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/miei")
    public List<ViaggioResponse> getViaggiMiei(Authentication authentication) {

        Utente utente = (Utente) authentication.getPrincipal();

        return viaggioService.trovaViaggiPerUtente(utente.getId())
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/oggi")
    public List<ViaggioResponse> getViaggiPerOggi() {

        return viaggioService.trovaViaggiDiOggi()
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }
}