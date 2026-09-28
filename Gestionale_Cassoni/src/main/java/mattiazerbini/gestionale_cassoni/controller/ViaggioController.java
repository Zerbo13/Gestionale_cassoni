package mattiazerbini.gestionale_cassoni.controllers;

import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import mattiazerbini.gestionale_cassoni.services.ViaggioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@CrossOrigin(origins = "*")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
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

    @PostMapping
    public Viaggio creaViaggio(@RequestBody Viaggio viaggio) {
        return viaggioService.salvaViaggio(viaggio);
    }
}