package cl.douc.edutech.serviciosoporte.repository;

import cl.douc.edutech.serviciosoporte.model.Soporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoporteRepository extends JpaRepository<Soporte, Long> {
}
