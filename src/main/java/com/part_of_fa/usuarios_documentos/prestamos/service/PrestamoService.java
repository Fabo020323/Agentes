package com.part_of_fa.usuarios_documentos.prestamos.service;

import com.part_of_fa.usuarios_documentos.enums.EstadoPrestamo;
import com.part_of_fa.usuarios_documentos.prestamos.entity.Prestamo;
import com.part_of_fa.usuarios_documentos.prestamos.repository.PrestamoRepository;
import com.part_of_fa.usuarios_documentos.utils.exceptions.InvalidPrestamoException;
import com.part_of_fa.usuarios_documentos.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    public Optional<Prestamo> findById(String id) {
        return prestamoRepository.findById(id);
    }

    public Prestamo save(Prestamo prestamo) {
        validatePrestamo(prestamo);
        return prestamoRepository.save(prestamo);
    }

    public void deleteById(String id) {
        prestamoRepository.deleteById(id);
    }

    // Actualiza el estado del préstamo
    public Prestamo updatePrestamoStatus(String id, EstadoPrestamo nuevoEstado) {
        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }

        Optional<Prestamo> prestamoOptional = prestamoRepository.findById(id);
        if (prestamoOptional.isPresent()) {
            Prestamo prestamo = prestamoOptional.get();
            prestamo.setEstado(nuevoEstado);
            return prestamoRepository.save(prestamo);
        } else {
            throw new ResourceNotFoundException("Prestamo not found with id: " + id);
        }
    }

    // Valida la información del préstamo
    private void validatePrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser nulo.");
        }
        if (prestamo.getFechaPrestamo() == null) {
            throw new IllegalArgumentException("La fecha de préstamo es obligatoria.");
        }
        if (prestamo.getFechaDevolucion() != null && prestamo.getFechaDevolucion().before(prestamo.getFechaPrestamo())) {
            throw new InvalidPrestamoException("La fecha de devolución no puede ser anterior a la fecha de préstamo.");
        }
        if (prestamo.getEstado() == null) {
            throw new IllegalArgumentException("El estado del préstamo es obligatorio.");
        }
    }
}