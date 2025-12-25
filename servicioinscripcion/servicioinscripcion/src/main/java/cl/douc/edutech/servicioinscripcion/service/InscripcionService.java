package cl.douc.edutech.servicioinscripcion.service;

import cl.douc.edutech.servicioinscripcion.model.Inscripcion;
import cl.douc.edutech.servicioinscripcion.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    public Inscripcion crear(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public Inscripcion actualizar(Long id, Inscripcion nueva) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.setIdUsuario(nueva.getIdUsuario());
            inscripcion.setIdCurso(nueva.getIdCurso());
            inscripcion.setFechaInscripcion(nueva.getFechaInscripcion());
            inscripcion.setConfirmada(nueva.isConfirmada());
            return inscripcionRepository.save(inscripcion);
        }).orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}

