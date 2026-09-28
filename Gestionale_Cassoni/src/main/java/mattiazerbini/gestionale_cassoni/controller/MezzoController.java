package mattiazerbini.gestionale_cassoni.controllers;

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
}