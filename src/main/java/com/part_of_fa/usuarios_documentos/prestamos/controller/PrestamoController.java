package com.part_of_fa.usuarios_documentos.prestamos.controller;

import com.part_of_fa.usuarios_documentos.enums.EstadoPrestamo;
import com.part_of_fa.usuarios_documentos.prestamos.entity.Prestamo;
import com.part_of_fa.usuarios_documentos.prestamos.service.PrestamoService;
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
    public Prestamo createPrestamo(@RequestBody Prestamo prestamo) {
        return prestamoService.save(prestamo);
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestamo(@PathVariable String id) {
        prestamoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/prestamo")
    public ResponseEntity<Prestamo> updatePrestamoStatus(
        @RequestBody Prestamo prestamo,
        @RequestParam(required = true) EstadoPrestamo nuevoEstado) {
    
       Prestamo actualizadoPrestamo = prestamoService.updatePrestamoStatus(prestamo, nuevoEstado);

       return new ResponseEntity<>(actualizadoPrestamo, HttpStatus.OK);
    }
}