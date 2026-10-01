package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.entities.Mezzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MezzoRepository extends JpaRepository<Mezzo, Long> {

    List<Mezzo> findByAttivoTrue();

    boolean existsByTargaIgnoreCase(String targa);

    boolean existsByTargaIgnoreCaseAndIdNot(String targa, Long id);
}