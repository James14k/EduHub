package cl.douc.edutech.servicioevaluacion.controller;

import cl.douc.edutech.servicioevaluacion.model.Evaluacion;
import cl.douc.edutech.servicioevaluacion.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public List<Evaluacion> listar() {
        return evaluacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> obtener(@PathVariable Long id) {
        return evaluacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evaluacion crear(@RequestBody Evaluacion evaluacion) {
        return evaluacionService.crear(evaluacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> actualizar(@PathVariable Long id, @RequestBody Evaluacion evaluacion) {
        try {
            return ResponseEntity.ok(evaluacionService.actualizar(id, evaluacion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        evaluacionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

