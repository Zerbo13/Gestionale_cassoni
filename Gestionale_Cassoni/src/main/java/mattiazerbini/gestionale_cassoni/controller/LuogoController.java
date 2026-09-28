package mattiazerbini.gestionale_cassoni.controllers;

import mattiazerbini.gestionale_cassoni.entities.Luogo;
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

    @GetMapping("/{id}")
    public ResponseEntity<Luogo> getLuogoPerId(@PathVariable Long id) {
        return luogoService.trovaLuogoPerId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Luogo creaLuogo(@RequestBody Luogo luogo) {
        return luogoService.salvaLuogo(luogo);
    }
}