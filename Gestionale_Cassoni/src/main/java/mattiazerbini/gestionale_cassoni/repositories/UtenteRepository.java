package mattiazerbini.gestionale_cassoni.repositories;

import mattiazerbini.gestionale_cassoni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByNicknameIgnoreCase(String nickname);

    boolean existsByNickname(String nickname);

    List<Utente> findByAttivoTrue();
}