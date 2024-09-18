package com.part_of_fa.usuarios_documentos.solicitudes.controller;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.solicitudes.entity.Solicitud;
import com.part_of_fa.usuarios_documentos.solicitudes.service.SolicitudService;
import com.part_of_fa.usuarios_documentos.user.entity.User;
import com.part_of_fa.usuarios_documentos.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping
    public List<Solicitud> getAllSolicitudes() {
        return solicitudService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getSolicitudById(@PathVariable String id) {
        return solicitudService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Solicitud createSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.save(solicitud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable String id) {
        solicitudService.deleteById(id);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Solicitud>> getSolicitudesByUserId(@PathVariable String userId) {
        try {
            List<Solicitud> solicitudes = solicitudService.findSolicitudesByUserId(userId);
            return ResponseEntity.ok(solicitudes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/docu/{docuId}")
    public ResponseEntity<List<Solicitud>> getSolicitudesByDocuId(@PathVariable String docuId) {
        try {
            List<Solicitud> solicitudes = solicitudService.findSolicitudesByDocuId(docuId);
            return ResponseEntity.ok(solicitudes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/user/{userId}/docu/{docuId}")
    public ResponseEntity<Solicitud> getSolicitudByUserIdAndDocuId(@PathVariable String userId, @PathVariable String docuId) {
        try {
            Solicitud solicitud = solicitudService.findSolicitudByUserIdAndDocuId(userId, docuId);
            return ResponseEntity.ok(solicitud);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
}