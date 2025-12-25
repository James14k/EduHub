package cl.douc.edutech.serviciopago.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    private Long idInscripcion;

    private Double monto;

    private LocalDate fechaPago;

    private String metodo; // Ej: "Transferencia", "Tarjeta"
}

