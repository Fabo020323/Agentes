package com.part_of_fa.usuarios_documentos.document.repository;


import com.part_of_fa.usuarios_documentos.document.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
