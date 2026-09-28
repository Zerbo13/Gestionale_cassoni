package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MezzoRepository extends JpaRepository<Mezzo, Long> {
}