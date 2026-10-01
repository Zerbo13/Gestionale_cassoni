package mattiazerbini.gestionale_cassoni.controller;

import jakarta.validation.Valid;
import mattiazerbini.gestionale_cassoni.dto.LoginRequest;
import mattiazerbini.gestionale_cassoni.dto.LoginResponse;
import mattiazerbini.gestionale_cassoni.entities.Utente;
import mattiazerbini.gestionale_cassoni.exceptions.BadRequestException;
import mattiazerbini.gestionale_cassoni.exceptions.ForbiddenException;
import mattiazerbini.gestionale_cassoni.security.JWTTools;
import mattiazerbini.gestionale_cassoni.services.UtenteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UtenteService utenteService;
    private final PasswordEncoder passwordEncoder;
    private final JWTTools jwtTools;

    public AuthController(
            UtenteService utenteService,
            PasswordEncoder passwordEncoder,
            JWTTools jwtTools
    ) {
        this.utenteService = utenteService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTools = jwtTools;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {

        Utente utente = utenteService.trovaPerNickname(
                loginRequest.getNickname()
        );

        if (!utente.getAttivo()) {
            throw new ForbiddenException(
                    "Utente disattivato"
            );
        }

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                utente.getPassword()
        )) {
            throw new BadRequestException(
                    "Password non corretta"
            );
        }

        String token = jwtTools.generateToken(utente);

        return new LoginResponse(
                token,
                utente.getNome(),
                utente.getCognome(),
                utente.getRuolo().name()
        );
    }
}