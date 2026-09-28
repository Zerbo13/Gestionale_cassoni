package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.entities.Cassone;
import mattiazerbini.gestionale_cassoni.services.CassoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cassoni")
@CrossOrigin(origins = "*")
public class CassoneController {

    private final CassoneService cassoneService;

    public CassoneController(CassoneService cassoneService) {
        this.cassoneService = cassoneService;
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
    public Cassone creaCassone(@RequestBody Cassone cassone) {
        return cassoneService.salvaCassone(cassone);
    }

    @PutMapping("/{id}")
    public Cassone modificaCassone(
            @PathVariable Long id,
            @RequestBody Cassone cassone
    ) {
        return cassoneService.modificaCassone(id, cassone);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Void> disattivaCassone(@PathVariable Long id) {

        cassoneService.disattivaCassone(id);

        return ResponseEntity.noContent().build();
    }
}