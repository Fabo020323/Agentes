package com.part_of_fa.usuarios_documentos.ejemplares.controller;

import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import com.part_of_fa.usuarios_documentos.ejemplares.service.EjemplarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejemplares")
@AllArgsConstructor
public class EjemplarController {

    private final EjemplarService ejemplarService;

    // Crear un nuevo ejemplar
    @PostMapping
    public ResponseEntity<Ejemplar> createEjemplar(@RequestBody Ejemplar ejemplar) {
        Ejemplar createdEjemplar = ejemplarService.createEjemplar(ejemplar);
        if (createdEjemplar != null) {
            return ResponseEntity.ok(createdEjemplar);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener un ejemplar por su código de barras
    @GetMapping("/{cod_barra}")
    public ResponseEntity<Ejemplar> getEjemplar(@PathVariable String cod_barra) {
        Ejemplar ejemplar = ejemplarService.getEjemplar(cod_barra);
        if (ejemplar != null) {
            return ResponseEntity.ok(ejemplar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un ejemplar por su código de barras
    @PutMapping("/{cod_barra}")
    public ResponseEntity<Ejemplar> updateEjemplar(@PathVariable String cod_barra, @RequestBody Ejemplar nuevoEjemplar) {
        Ejemplar updatedEjemplar = ejemplarService.updateEjemplar(cod_barra, nuevoEjemplar);
        if (updatedEjemplar != null) {
            return ResponseEntity.ok(updatedEjemplar);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un ejemplar por su código de barras
    @DeleteMapping("/{cod_barra}")
    public ResponseEntity<Void> deleteEjemplar(@PathVariable String cod_barra) {
        Ejemplar deletedEjemplar = ejemplarService.deleteEjemplar(cod_barra);
        if (deletedEjemplar != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
