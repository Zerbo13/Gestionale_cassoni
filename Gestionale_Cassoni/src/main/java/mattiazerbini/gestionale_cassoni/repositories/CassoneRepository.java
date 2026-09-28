package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.enties.Cassone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CassoneRepository extends JpaRepository<Cassone, Long> {
}
