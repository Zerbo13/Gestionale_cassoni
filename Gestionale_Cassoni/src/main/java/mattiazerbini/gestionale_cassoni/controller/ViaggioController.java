package mattiazerbini.gestionale_cassoni.controller;

import jakarta.validation.Valid;
import mattiazerbini.gestionale_cassoni.dto.ViaggioRequest;
import mattiazerbini.gestionale_cassoni.dto.ViaggioResponse;
import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.entities.Ruolo;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.ForbiddenException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.services.CassoneService;
import mattiazerbini.gestionale_cassoni.services.LuogoService;
import mattiazerbini.gestionale_cassoni.services.MezzoService;
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
    private final MezzoService mezzoService;

    public ViaggioController(
            ViaggioService viaggioService,
            CassoneService cassoneService,
            LuogoService luogoService,
            MezzoService mezzoService
    ) {
        this.viaggioService = viaggioService;
        this.cassoneService = cassoneService;
        this.luogoService = luogoService;
        this.mezzoService = mezzoService;
    }

    @GetMapping
    public List<ViaggioResponse> getTuttiIViaggi() {

        return viaggioService
                .trovaTuttiIViaggi()
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

        Mezzo mezzo = mezzoService
                .trovaMezzoPerId(request.getMezzoId())
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

        if (!mezzo.getAttivo()) {
            throw new BadRequestException("Il mezzo selezionato è disattivato");
        }

        Cassone cassone = cassoneService
                .trovaCassonePerId(request.getCassoneId())
                .orElseThrow(() -> new NotFoundException("Cassone non trovato"));

        if (!cassone.getAttivo()) {
            throw new BadRequestException("Il cassone è disattivato");
        }

        Luogo destinazione = luogoService
                .trovaLuogoPerId(request.getDestinazioneId())
                .orElseThrow(() -> new NotFoundException("Destinazione non trovata"));

        if (!destinazione.getAttivo()) {
            throw new BadRequestException("La destinazione è disattivata");
        }

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

        Viaggio viaggio = viaggioService
                .trovaViaggioPerId(id)
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato"));

        if (utente.getRuolo() != Ruolo.ADMIN && !viaggio.getUtente().getId().equals(utente.getId())) {
            throw new ForbiddenException("Non puoi annullare il viaggio di un altro operaio");
        }

        Viaggio viaggioAnnullato = viaggioService.annullaViaggio(id);

        return viaggioService.convetiInResponse(viaggioAnnullato);
    }

    @GetMapping("/cassone/{cassoneId}")
    public List<ViaggioResponse> getViaggiPerCassone(
            @PathVariable Long cassoneId
    ) {

        return viaggioService
                .trovaViaggiPerCassone(cassoneId)
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/miei")
    public List<ViaggioResponse> getViaggiMiei(Authentication authentication) {

        Utente utente = (Utente) authentication.getPrincipal();

        return viaggioService
                .trovaViaggiPerUtente(utente.getId())
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/oggi")
    public List<ViaggioResponse> getViaggiPerOggi() {

        return viaggioService
                .trovaViaggiDiOggi()
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }
}