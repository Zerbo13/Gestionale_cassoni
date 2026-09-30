package mattiazerbini.gestionale_cassoni.controller;

import jakarta.validation.Valid;
import mattiazerbini.gestionale_cassoni.dto.LuogoRequest;
import mattiazerbini.gestionale_cassoni.entities.Luogo;
import mattiazerbini.gestionale_cassoni.exceptions.NotFoundException;
import mattiazerbini.gestionale_cassoni.services.LuogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/luoghi")
@CrossOrigin(origins = "*")
public class LuogoController {

    private final LuogoService luogoService;

    public LuogoController(LuogoService luogoService) {
        this.luogoService = luogoService;
    }

    @GetMapping
    public List<Luogo> getTuttiILuoghi() {
        return luogoService.trovaTuttiILuoghi();
    }

    @GetMapping("/attivi")
    public List<Luogo> getTuttiILuoghiAttivi() {
        return luogoService.trovaLuoghiAttivi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Luogo> getLuogoPerId(@PathVariable Long id) {

        Luogo luogo = luogoService
                .trovaLuogoPerId(id)
                .orElseThrow(() -> new NotFoundException("Luogo non trovato"));

        return ResponseEntity.ok(luogo);
    }

    @PostMapping
    public Luogo creaLuogo(@Valid @RequestBody LuogoRequest request) {

        Luogo luogo = new Luogo();

        luogo.setNome(request.getNome());
        luogo.setIndirizzo(request.getIndirizzo());
        luogo.setTipologia(request.getTipologia());
        luogo.setAttivo(true);

        return luogoService.salvaLuogo(luogo);
    }

    @PutMapping("/{id}")
    public Luogo modificaLuogo(@PathVariable Long id, @Valid @RequestBody LuogoRequest request) {

        Luogo luogo = luogoService
                .trovaLuogoPerId(id)
                .orElseThrow(() -> new NotFoundException("Luogo non trovato"));

        luogo.setNome(request.getNome());
        luogo.setIndirizzo(request.getIndirizzo());
        luogo.setTipologia(request.getTipologia());

        return luogoService.modificaLuogo(id, luogo);
    }

    @PutMapping("/{id}/disattiva")
    public ResponseEntity<Luogo> disattivaLuogo(@PathVariable Long id) {

        Luogo luogo = luogoService.disattivaLuogo(id);

        return ResponseEntity.ok(luogo);
    }

    @PutMapping("/{id}/attiva")
    public ResponseEntity<Luogo> attivaLuogo(@PathVariable Long id) {

        Luogo luogo = luogoService.attivaLuogo(id);

        return ResponseEntity.ok(luogo);
    }
}