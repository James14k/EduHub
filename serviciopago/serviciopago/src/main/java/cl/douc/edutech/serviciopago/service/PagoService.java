package cl.douc.edutech.serviciopago.service;

import cl.douc.edutech.serviciopago.model.Pago;
import cl.douc.edutech.serviciopago.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> listar() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    public Pago crear(Pago pago) {
        return pagoRepository.save(pago);
    }

    public Pago actualizar(Long id, Pago nuevo) {
        return pagoRepository.findById(id).map(p -> {
            p.setIdUsuario(nuevo.getIdUsuario());
            p.setIdInscripcion(nuevo.getIdInscripcion());
            p.setMonto(nuevo.getMonto());
            p.setFechaPago(nuevo.getFechaPago());
            p.setMetodo(nuevo.getMetodo());
            return pagoRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public void eliminar(Long id) {
        pagoRepository.deleteById(id);
    }
}
