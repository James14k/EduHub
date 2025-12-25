package cl.douc.edutech.serviciocursos.repository;

import cl.douc.edutech.serviciocursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}

