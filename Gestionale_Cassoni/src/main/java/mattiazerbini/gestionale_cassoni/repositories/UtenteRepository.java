package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
