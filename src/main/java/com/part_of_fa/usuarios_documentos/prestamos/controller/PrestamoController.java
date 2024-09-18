package com.part_of_fa.usuarios_documentos.prestamos.controller;

import com.part_of_fa.usuarios_documentos.enums.EstadoPrestamo;
import com.part_of_fa.usuarios_documentos.prestamos.entity.Prestamo;
import com.part_of_fa.usuarios_documentos.prestamos.service.PrestamoService;
import com.part_of_fa.usuarios_documentos.utils.exceptions.InvalidPrestamoException;
import com.part_of_fa.usuarios_documentos.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Prestamo> getAllPrestamos() {
        return prestamoService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamoById(@PathVariable String id) {
        return prestamoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Prestamo> createPrestamo(@RequestBody Prestamo prestamo) {
        try {
            Prestamo savedPrestamo = prestamoService.save(prestamo);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPrestamo);
        } catch (IllegalArgumentException | InvalidPrestamoException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable String id) {
        prestamoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}/updateStatus")
    public ResponseEntity<Prestamo> updatePrestamoStatus(
            @PathVariable String id,
            @RequestParam(required = true) EstadoPrestamo nuevoEstado) {

        try {
            Prestamo updatedPrestamo = prestamoService.updatePrestamoStatus(id, nuevoEstado);
            return ResponseEntity.ok(updatedPrestamo);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPrestamoException.class)
    public ResponseEntity<String> handleInvalidPrestamoException(InvalidPrestamoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}