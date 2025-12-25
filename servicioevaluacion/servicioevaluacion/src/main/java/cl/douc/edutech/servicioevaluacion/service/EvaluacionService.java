package cl.douc.edutech.servicioevaluacion.service;

import cl.douc.edutech.servicioevaluacion.model.Evaluacion;
import cl.douc.edutech.servicioevaluacion.repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    public Optional<Evaluacion> obtenerPorId(Long id) {
        return evaluacionRepository.findById(id);
    }

    public Evaluacion crear(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public Evaluacion actualizar(Long id, Evaluacion nueva) {
        return evaluacionRepository.findById(id).map(e -> {
            e.setIdUsuario(nueva.getIdUsuario());
            e.setIdCurso(nueva.getIdCurso());
            e.setNota(nueva.getNota());
            e.setObservaciones(nueva.getObservaciones());
            return evaluacionRepository.save(e);
        }).orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));
    }

    public void eliminar(Long id) {
        evaluacionRepository.deleteById(id);
    }
}

