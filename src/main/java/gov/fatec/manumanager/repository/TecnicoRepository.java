package gov.fatec.manumanager.repository;

import gov.fatec.manumanager.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
    Optional<Tecnico> findByUsuarioId(Long id);
}
