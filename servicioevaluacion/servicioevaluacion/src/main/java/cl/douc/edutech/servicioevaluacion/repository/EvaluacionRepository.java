package cl.douc.edutech.servicioevaluacion.repository;

import cl.douc.edutech.servicioevaluacion.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
}

