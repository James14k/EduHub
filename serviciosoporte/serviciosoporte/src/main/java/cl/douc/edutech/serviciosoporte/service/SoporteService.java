package cl.douc.edutech.serviciosoporte.service;

import cl.douc.edutech.serviciosoporte.model.Soporte;
import cl.douc.edutech.serviciosoporte.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> listar() {
        return soporteRepository.findAll();
    }

    public Optional<Soporte> obtenerPorId(Long id) {
        return soporteRepository.findById(id);
    }

    public Soporte crear(Soporte soporte) {
        soporte.setFechaCreacion(LocalDateTime.now());
        soporte.setEstado("Pendiente");
        return soporteRepository.save(soporte);
    }

    public Soporte actualizar(Long id, Soporte nuevo) {
        return soporteRepository.findById(id).map(s -> {
            s.setTipo(nuevo.getTipo());
            s.setDescripcion(nuevo.getDescripcion());
            s.setEstado(nuevo.getEstado());
            return soporteRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Soporte no encontrado"));
    }

    public void eliminar(Long id) {
        soporteRepository.deleteById(id);
    }
}
