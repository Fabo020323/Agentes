package com.part_of_fa.usuarios_documentos.solicitudes.service;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.solicitudes.entity.Solicitud;
import com.part_of_fa.usuarios_documentos.solicitudes.repository.SolicitudRepository;
import com.part_of_fa.usuarios_documentos.user.entity.User;
import com.part_of_fa.usuarios_documentos.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }

    public Optional<Solicitud> findById(String id) {
        return solicitudRepository.findById(id);
    }

    public Solicitud save(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    public void deleteById(String id) {
        solicitudRepository.deleteById(id);
    }

    // Buscar solicitudes por un usuario con validaciones
    public List<Solicitud> findSolicitudesByUserId(String userId) {
        validateUserId(userId);

        List<Solicitud> solicitudes = solicitudRepository.findByUser_Id(userId);
        if (solicitudes == null || solicitudes.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron solicitudes para el usuario con ID: " + userId);
        }
        return solicitudes;
    }

    // Buscar solicitudes por un documento con validaciones
    public List<Solicitud> findSolicitudesByDocuId(String docuId) {
        validateDocuId(docuId);

        List<Solicitud> solicitudes = solicitudRepository.findByDocu_Id(docuId);
        if (solicitudes == null || solicitudes.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron solicitudes para el documento con ID: " + docuId);
        }
        return solicitudes;
    }

    // Buscar una sola solicitud por usuario y documento con validaciones
    public Solicitud findSolicitudByUserIdAndDocuId(String userId, String docuId) {
        validateUserId(userId);
        validateDocuId(docuId);

        Solicitud solicitud = solicitudRepository.findByUser_IdAndDocu_Id(userId, docuId);
        if (solicitud == null) {
            throw new ResourceNotFoundException("No se encontró ninguna solicitud para el usuario con ID: " + userId + " y el documento con ID: " + docuId);
        }
        return solicitud;
    }

    // Validación del userId
    private void validateUserId(String userId) {
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("El ID del usuario no puede estar vacío.");
        }
    }

    // Validación del docuId
    private void validateDocuId(String docuId) {
        if (!StringUtils.hasText(docuId)) {
            throw new IllegalArgumentException("El ID del documento no puede estar vacío.");
        }
    }
}    