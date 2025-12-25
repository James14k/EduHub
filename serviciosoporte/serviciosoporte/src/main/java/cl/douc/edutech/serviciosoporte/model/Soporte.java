package cl.douc.edutech.serviciosoporte.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "soporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    private String tipo; // "Reclamo", "Consulta", "Sugerencia"

    private String descripcion;

    private String estado; // "Pendiente", "En proceso", "Resuelto"

    private LocalDateTime fechaCreacion;
}

