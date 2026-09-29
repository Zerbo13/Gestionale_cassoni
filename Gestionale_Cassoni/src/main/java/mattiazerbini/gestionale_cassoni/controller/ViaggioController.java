package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.dto.ViaggioRequest;
import mattiazerbini.gestionale_cassoni.dto.ViaggioResponse;
import mattiazerbini.gestionale_cassoni.entities.*;
import mattiazerbini.gestionale_cassoni.services.CassoneService;
import mattiazerbini.gestionale_cassoni.services.LuogoService;
import mattiazerbini.gestionale_cassoni.services.ViaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

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
    public List<ViaggioResponse> getTuttiIViaggi() {

        return viaggioService.trovaTuttiIViaggi()
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaggio> getViaggioPerId(@PathVariable Long id) {
        return viaggioService.trovaViaggioPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/avvia")
    public ViaggioResponse avviaViaggio(
            @RequestBody ViaggioRequest request,
            Authentication authentication
    ) {

        Utente utente = (Utente) authentication.getPrincipal();

        Mezzo mezzo = utente.getMezzo();

        if (mezzo == null) {
            throw new RuntimeException(
                    "Nessun mezzo assegnato all'utente!"
            );
        }

        Cassone cassone = cassoneService
                .trovaCassonePerId(request.getCassoneId())
                .orElseThrow(() ->
                        new RuntimeException("Cassone non trovato")
                );

        Luogo destinazione = luogoService
                .trovaLuogoPerId(request.getDestinazioneId())
                .orElseThrow(() ->
                        new RuntimeException("Destinazione non trovata")
                );

        Viaggio viaggio = new Viaggio();

        viaggio.setUtente(utente);
        viaggio.setMezzo(mezzo);
        viaggio.setCassone(cassone);
        viaggio.setDestinazione(destinazione);
        viaggio.setNote(request.getNote());

        Viaggio viaggioSalvato =
                viaggioService.avviaViaggio(viaggio);

        return viaggioService.convetiInResponse(viaggioSalvato);
    }

    @PutMapping("/{id}/chiudi")
    public ViaggioResponse chiudiViaggio(@PathVariable Long id, Authentication authentication) {
        Utente utente = (Utente) authentication.getPrincipal();
        Viaggio viaggio = viaggioService.trovaViaggioPerId(id)
                .orElseThrow(()-> new RuntimeException("Viaggio non trovato"));

        if (utente.getRuolo() != Ruolo.ADMIN && !viaggio.getUtente().getId().equals(utente.getId())) {
            throw new RuntimeException("Non puoi chiiudere il viaggio di un altro operaio");
        }

        Viaggio viaggioChiuso = viaggioService.chiudiViaggio(id);
        return viaggioService.convetiInResponse(viaggioChiuso);
    }

    @PutMapping("/{id}/annulla")
    public ViaggioResponse annullaViaggio(@PathVariable Long id, Authentication authentication) {
        Utente utente = (Utente) authentication.getPrincipal();
        Viaggio viaggio = viaggioService.trovaViaggioPerId(id)
                .orElseThrow(()-> new RuntimeException("Viaggio non trovato"));

        if (utente.getRuolo() != Ruolo.ADMIN && !viaggio.getUtente().getId().equals(utente.getId())) {
            throw new RuntimeException("Non puoi annullare il viaggio di un altro operaio");
        }

        Viaggio viaggioAnnullato = viaggioService.annullaViaggio(id);
        return viaggioService.convetiInResponse(viaggioAnnullato);
    }

    @GetMapping("/cassone/{cassoneId}")
    public List<ViaggioResponse> getViaggiPerCassone(
            @PathVariable("cassoneId") Long cassoneId
    ) {
        return viaggioService.trovaViaggiPerCassone(cassoneId)
                .stream()
                .map(viaggioService::convetiInResponse)
                .toList();
    }

    @GetMapping("/miei")
    public List<ViaggioResponse> getViaggiMiei(Authentication authentication) {
        Utente utente =(Utente) authentication.getPrincipal();
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