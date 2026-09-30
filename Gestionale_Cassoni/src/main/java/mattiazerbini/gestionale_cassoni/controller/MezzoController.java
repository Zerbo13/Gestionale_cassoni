package mattiazerbini.gestionale_cassoni.controller;

import jakarta.validation.Valid;
import mattiazerbini.gestionale_cassoni.dto.MezzoRequest;
import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
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

    @GetMapping("/attivi")
    public List<Mezzo> getMezziAttivi() {
        return mezzoService.trovaMezziAttivi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mezzo> getMezzoPerId(@PathVariable Long id) {

        Mezzo mezzo = mezzoService
                .trovaMezzoPerId(id)
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

        return ResponseEntity.ok(mezzo);
    }

    @PostMapping
    public Mezzo creaMezzo(@Valid @RequestBody MezzoRequest request) {

        Mezzo mezzo = new Mezzo();

        mezzo.setTarga(request.getTarga());
        mezzo.setModello(request.getModello());
        mezzo.setTipologia(request.getTipo());
        mezzo.setAttivo(true);

        return mezzoService.salvaMezzo(mezzo);
    }

    @PutMapping("/{id}")
    public Mezzo modificaMezzo(@PathVariable Long id, @Valid @RequestBody MezzoRequest request) {

        Mezzo mezzo = mezzoService
                .trovaMezzoPerId(id)
                .orElseThrow(() -> new NotFoundException("Mezzo non trovato"));

        mezzo.setTarga(request.getTarga());
        mezzo.setModello(request.getModello());
        mezzo.setTipologia(request.getTipo());

        return mezzoService.modificaMezzo(id, mezzo);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Mezzo> disattivaMezzo(@PathVariable Long id) {

        Mezzo mezzo = mezzoService.disattivaMezzo(id);

        return ResponseEntity.ok(mezzo);
    }

    @PutMapping("/{id}/attiva")
    public ResponseEntity<Mezzo> attivaMezzo(@PathVariable Long id) {

        Mezzo mezzo = mezzoService.attivaMezzo(id);

        return ResponseEntity.ok(mezzo);
    }
}