package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.enties.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {
}
