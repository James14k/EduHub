package cl.douc.edutech.servicioinscripcion.repository;

import cl.douc.edutech.servicioinscripcion.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}

