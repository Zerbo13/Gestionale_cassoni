package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.entities.StatoViaggio;
import mattiazerbini.gestionale_cassoni.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {

    Optional<Viaggio> findFirstByCassoneIdAndStatoOrderByDataOraFineDesc(
            Long cassoneId,
            StatoViaggio stato
    );

    Optional<Viaggio> findFirstByCassoneIdAndStatoOrderByDataOraInizioDesc(
            Long cassoneId,
            StatoViaggio stato
    );

    List<Viaggio> findByCassoneIdOrderByDataOraInizioDesc(Long cassoneId);

    List<Viaggio> findByUtenteIdOrderByDataOraInizioDesc(Long utenteId);

    List<Viaggio> findByDataOraInizioBetweenOrderByDataOraInizioDesc(
            LocalDateTime inizio,
            LocalDateTime fine
    );
}