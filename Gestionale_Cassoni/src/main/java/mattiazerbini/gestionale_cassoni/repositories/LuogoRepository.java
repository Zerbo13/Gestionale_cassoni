package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.entities.Luogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LuogoRepository extends JpaRepository<Luogo, Long> {
    List<Luogo> findByAttivoTrue();
}
