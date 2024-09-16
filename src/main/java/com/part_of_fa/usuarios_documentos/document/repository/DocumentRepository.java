package com.part_of_fa.usuarios_documentos.document.repository;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Docu, String> {
}