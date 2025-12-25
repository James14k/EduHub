package cl.douc.edutech.servicioevaluacion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    private Long idCurso;

    private Double nota;

    private String observaciones; // Comentarios del docente, si los hay
}

