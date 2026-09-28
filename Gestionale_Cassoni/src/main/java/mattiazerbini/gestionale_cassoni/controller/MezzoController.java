package mattiazerbini.gestionale_cassoni.controller;

import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.services.MezzoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mezzi")
@CrossOrigin(origins = "*")
public class MezzoController {

    private final MezzoService mezzoService;

    public MezzoController(MezzoService mezzoService) {
        this.mezzoService = mezzoService;
    }

    @GetMapping
    public List<Mezzo> getTuttiIMezzi() {
        return mezzoService.trovaTuttiIMezzi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mezzo> getMezzoPerId(@PathVariable Long id) {
        return mezzoService.trovaMezzoPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mezzo creaMezzo(@RequestBody Mezzo mezzo) {
        return mezzoService.salvaMezzo(mezzo);
    }

    @PutMapping("/{id}")
    public Mezzo modificaMezzo(
            @PathVariable Long id,
            @RequestBody Mezzo mezzo
    ) {
        return mezzoService.modificaMezzo(id, mezzo);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Void> disattivaMezzo(@PathVariable Long id) {
        mezzoService.disattivaMezzo(id);
        return ResponseEntity.noContent().build();
    }
}