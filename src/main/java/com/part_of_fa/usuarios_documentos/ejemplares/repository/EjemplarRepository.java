package com.part_of_fa.usuarios_documentos.ejemplares.repository;

import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EjemplarRepository extends MongoRepository<Ejemplar, String> {

}