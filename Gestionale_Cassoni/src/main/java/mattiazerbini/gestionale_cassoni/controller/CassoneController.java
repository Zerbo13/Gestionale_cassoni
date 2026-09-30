package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.dto.CassoneRequest;
import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.services.CassoneService;
import mattiazerbini.gestionale_cassoni.services.LuogoService;
import mattiazerbini.gestionale_cassoni.services.ViaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            LuogoService luogoService) {
        this.cassoneService = cassoneService;
        this.viaggioService = viaggioService;
        this.luogoService = luogoService;
    }
    @GetMapping
    public List<Cassone> getTuttiICassoni() {
        return cassoneService.trovaTuttiICassoni();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cassone> getCassonePerId(@PathVariable Long id) {
        return cassoneService.trovaCassonePerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cassone creaCassone(@RequestBody CassoneRequest request) {

        Luogo posizioneIniziale = luogoService.trovaLuogoPerId(request.getPosizioneInizialeId())
                .orElseThrow(() -> new RuntimeException("Posizione iniziale non trovata"));

        Cassone cassone = new Cassone();
        cassone.setCodiceCassone(request.getCodiceCassone());
        cassone.setColore(request.getColore());
        cassone.setMisura(request.getMisura());
        cassone.setTipologia(request.getTipologia());
        cassone.setCapacità(request.getCapacità());
        cassone.setPosizioneIniziale(posizioneIniziale);
        cassone.setAttivo(true);

        return cassoneService.salvaCassone(cassone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cassone> modificaCassone(@PathVariable Long id, @RequestBody CassoneRequest request) {

        Cassone cassone = cassoneService
                .trovaCassonePerId(id)
                .orElseThrow(() -> new RuntimeException("Cassone non trovato"));

        Luogo posizioneIniziale = luogoService
                .trovaLuogoPerId(request.getPosizioneInizialeId())
                .orElseThrow(() -> new RuntimeException("Posizione iniziale non trovata"));

        cassone.setCodiceCassone(request.getCodiceCassone());
        cassone.setColore(request.getColore());
        cassone.setMisura(request.getMisura());
        cassone.setTipologia(request.getTipologia());
        cassone.setCapacità(request.getCapacità());
        cassone.setPosizioneIniziale(posizioneIniziale);

        Cassone cassoneModificato = cassoneService.modificaCassone(id, cassone);

        return ResponseEntity.ok(cassoneModificato);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Cassone> disattivaCassone(@PathVariable Long id) {
        Cassone cassone = cassoneService.disattivaCassone(id);

        return ResponseEntity.ok(cassone);
    }


    @GetMapping("/{id}/posizione")
    public ResponseEntity<String> getPosizioneAttuale(@PathVariable Long id) {

        return cassoneService
                .trovaCassonePerId(id)
                .map(cassone -> ResponseEntity.ok(
                                viaggioService.trovaPosizioneAttualeCassone(cassone)))
                .orElse(ResponseEntity
                                .notFound()
                                .build());
    }
}