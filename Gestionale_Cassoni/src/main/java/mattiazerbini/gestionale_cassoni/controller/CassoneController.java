package mattiazerbini.gestionale_cassoni.controller;

import jakarta.validation.Valid;
import mattiazerbini.gestionale_cassoni.dto.CassoneRequest;
import mattiazerbini.gestionale_cassoni.dto.PosizioneCassoneResponse;
import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.services.CassoneService;
import mattiazerbini.gestionale_cassoni.services.LuogoService;
import mattiazerbini.gestionale_cassoni.services.ViaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/cassoni")
@CrossOrigin(origins = "*")
public class CassoneController {

    private final CassoneService cassoneService;
    private final ViaggioService viaggioService;
    private final LuogoService luogoService;

    public CassoneController(
            CassoneService cassoneService,
            ViaggioService viaggioService,
            LuogoService luogoService
    ) {
        this.cassoneService = cassoneService;
        this.viaggioService = viaggioService;
        this.luogoService = luogoService;
    }

    // Restituisce tutti i cassoni
    @GetMapping
    public List<Cassone> getTuttiICassoni() {
        return cassoneService.trovaTuttiICassoni();
    }

    // Restituisce un cassone specifico
    @GetMapping("/{id}")
    public ResponseEntity<Cassone> getCassonePerId(
            @PathVariable Long id
    ) {
        return cassoneService.trovaCassonePerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crea un nuovo cassone
    @PostMapping
    public Cassone creaCassone(
            @Valid @RequestBody CassoneRequest request
    ) {
        if (request.getPosizioneInizialeId() == null) {
            throw new BadRequestException(
                    "Seleziona la posizione iniziale del cassone"
            );
        }

        Luogo posizioneIniziale = luogoService
                .trovaLuogoPerId(request.getPosizioneInizialeId())
                .orElseThrow(() ->
                        new NotFoundException(
                                "Posizione iniziale non trovata"
                        )
                );

        Cassone cassone = new Cassone();

        cassone.setCodiceCassone(request.getCodiceCassone());
        cassone.setColore(request.getColore());
        cassone.setMisura(request.getMisura());
        cassone.setTipologia(request.getTipologia());
        cassone.setCapacità(request.getCapacità());

        cassone.setPosizioneIniziale(posizioneIniziale);
        cassone.setDataPosizioneIniziale(LocalDateTime.now());

        // Un cassone appena creato è attivo
        cassone.setAttivo(true);

        return cassoneService.salvaCassone(cassone);
    }

    // Modifica un cassone esistente
    @PutMapping("/{id}")
    public ResponseEntity<Cassone> modificaCassone(
            @PathVariable Long id,
            @Valid @RequestBody CassoneRequest request
    ) {
        // Recuperiamo il cassone esistente
        Cassone cassone = cassoneService.trovaCassonePerId(id)
                .orElseThrow(() ->
                        new NotFoundException("Cassone non trovato")
                );

        // Aggiorniamo i campi obbligatori
        cassone.setCodiceCassone(request.getCodiceCassone());
        cassone.setColore(request.getColore());
        cassone.setMisura(request.getMisura());

        // I campi facoltativi vengono modificati solo se inviati
        if (request.getTipologia() != null) {
            cassone.setTipologia(request.getTipologia());
        }

        if (request.getCapacità() != null) {
            cassone.setCapacità(request.getCapacità());
        }

        // La posizione iniziale non è obbligatoria nelle modifiche
        if (request.getPosizioneInizialeId() != null) {

            Luogo nuovaPosizione = luogoService
                    .trovaLuogoPerId(request.getPosizioneInizialeId())
                    .orElseThrow(() ->
                            new NotFoundException(
                                    "Posizione iniziale non trovata"
                            )
                    );

            Long vecchiaPosizioneId = null;

            if (cassone.getPosizioneIniziale() != null) {
                vecchiaPosizioneId =
                        cassone.getPosizioneIniziale().getId();
            }

            // Aggiorniamo la data solo se cambia la posizione
            if (!Objects.equals(
                    vecchiaPosizioneId,
                    nuovaPosizione.getId()
            )) {
                cassone.setDataPosizioneIniziale(
                        LocalDateTime.now()
                );
            }

            cassone.setPosizioneIniziale(nuovaPosizione);
        }

        // Lo stato attivo viene gestito dagli endpoint dedicati
        Cassone risultato =
                cassoneService.modificaCassone(id, cassone);

        return ResponseEntity.ok(risultato);
    }

    // Attiva un cassone
    @PutMapping("/{id}/attiva")
    public ResponseEntity<Cassone> attivaCassone(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                cassoneService.attivaCassone(id)
        );
    }

    // Disattiva un cassone
    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Cassone> disattivaCassone(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                cassoneService.disattivaCassone(id)
        );
    }

    // Restituisce la posizione attuale del cassone
    @GetMapping("/{id}/posizione")
    public ResponseEntity<PosizioneCassoneResponse>
    getPosizioneAttuale(@PathVariable Long id) {

        Cassone cassone = cassoneService.trovaCassonePerId(id)
                .orElseThrow(() ->
                        new NotFoundException("Cassone non trovato")
                );

        PosizioneCassoneResponse posizione =
                viaggioService.trovaPosizioneAttualeCassone(cassone);

        return ResponseEntity.ok(posizione);
    }
}