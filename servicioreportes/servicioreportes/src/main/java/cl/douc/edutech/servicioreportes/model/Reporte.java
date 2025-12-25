package cl.douc.edutech.servicioreportes.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ej: "Usuarios por curso", "Pagos realizados", etc.

    private String descripcion;

    private String generadoPor; // admin, sistema, etc.

    private LocalDateTime fechaGeneracion;
}
