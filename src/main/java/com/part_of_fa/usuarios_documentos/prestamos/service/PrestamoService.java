package com.part_of_fa.usuarios_documentos.prestamos.service;

import com.part_of_fa.usuarios_documentos.enums.EstadoPrestamo;
import com.part_of_fa.usuarios_documentos.prestamos.entity.Prestamo;
import com.part_of_fa.usuarios_documentos.prestamos.repository.PrestamoRepository;
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
        return prestamoRepository.save(prestamo);
    }

    public void deleteById(String id) {
        prestamoRepository.deleteById(id);
    }
    //Actualiza el estado del prestamo
    public Prestamo updatePrestamoStatus(Prestamo prestamo, EstadoPrestamo nuevoEstado) {
        prestamo.setEstado(nuevoEstado);
        return prestamoRepository.save(prestamo);
    }
}