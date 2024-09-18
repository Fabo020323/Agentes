package com.part_of_fa.usuarios_documentos.solicitudes.repository;

import com.part_of_fa.usuarios_documentos.solicitudes.entity.Solicitud;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends MongoRepository<Solicitud, String> {
    // Definir método de consulta por ID de usuario
    List<Solicitud> findByUser_Id(String userId);

    // Definir método de consulta por ID de documento
    List<Solicitud> findByDocu_Id(String docuId);

    // Definir método para buscar una solicitud específica por usuario y documento
    Solicitud findByUser_IdAndDocu_Id(String userId, String docuId);
}