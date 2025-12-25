package cl.douc.edutech.serviciosoporte.controller;

import cl.douc.edutech.serviciosoporte.model.Soporte;
import cl.douc.edutech.serviciosoporte.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
@CrossOrigin(origins = "*")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public List<Soporte> listar() {
        return soporteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soporte> obtener(@PathVariable Long id) {
        return soporteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Soporte crear(@RequestBody Soporte soporte) {
        return soporteService.crear(soporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Soporte> actualizar(@PathVariable Long id, @RequestBody Soporte soporte) {
        try {
            return ResponseEntity.ok(soporteService.actualizar(id, soporte));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        soporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

