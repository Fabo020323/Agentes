package com.part_of_fa.usuarios_documentos.prestamos.repository;

import com.part_of_fa.usuarios_documentos.prestamos.entity.Prestamo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends MongoRepository<Prestamo, String> {
}