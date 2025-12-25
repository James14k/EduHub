package cl.douc.edutech.servicioreportes.repository;

import cl.douc.edutech.servicioreportes.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}
