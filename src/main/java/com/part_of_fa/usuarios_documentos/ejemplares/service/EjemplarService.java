package com.part_of_fa.usuarios_documentos.ejemplares.service;

import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import com.part_of_fa.usuarios_documentos.ejemplares.repository.EjemplarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor

public class EjemplarService {
    private final EjemplarRepository ejemplarRepository;

//    Create
    public Ejemplar createEjemplar(Ejemplar ejemplar) {
        if(ejemplar != null){
            return ejemplarRepository.save(ejemplar);
        }else{
            return null;
        }
    }
    // Get
    public Ejemplar getEjemplar(String cod) {
        Optional<Ejemplar> ejemplar = ejemplarRepository.findById(cod);
        return ejemplar.orElse(null);  // Devuelve null si no se encuentra el ejemplar
    }

    // Delete
    public Ejemplar deleteEjemplar(String cod) {
        Optional<Ejemplar> ejemplar = ejemplarRepository.findById(cod);
        if (ejemplar.isPresent()) {
            ejemplarRepository.delete(ejemplar.get());
            return ejemplar.get();
        }
        return null;
    }

    // Update
    public Ejemplar updateEjemplar(String cod, Ejemplar nuevoEjemplar) {
        Optional<Ejemplar> ejemplarOptional = ejemplarRepository.findById(cod);
        if (ejemplarOptional.isPresent()) {
            Ejemplar ejemplarExistente = ejemplarOptional.get();

            // Actualizar todos los campos del ejemplar excepto el código
            ejemplarExistente.setSubdivision_1(nuevoEjemplar.getSubdivision_1());
            ejemplarExistente.setSubdivision_2(nuevoEjemplar.getSubdivision_2());
            ejemplarExistente.setNo_ingreso(nuevoEjemplar.getNo_ingreso());
            ejemplarExistente.setFecha_ingreso(nuevoEjemplar.getFecha_ingreso());
            ejemplarExistente.setUbicacion(nuevoEjemplar.getUbicacion());
            ejemplarExistente.setAdquisicion_via(nuevoEjemplar.getAdquisicion_via());
            ejemplarExistente.setProcedencia(nuevoEjemplar.getProcedencia());
            ejemplarExistente.setPrecio(nuevoEjemplar.getPrecio());
            ejemplarExistente.setEstado(nuevoEjemplar.getEstado());

            // Guardar el ejemplar actualizado
            return ejemplarRepository.save(ejemplarExistente);
        }
        return null;
    }

}