package com.part_of_fa.usuarios_documentos.document.service;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.document.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Docu> findAll() {
        return documentRepository.findAll();
    }

    public Optional<Docu> findById(String id) {
        return documentRepository.findById(id);
    }

    public Docu save(Docu document) {
        return documentRepository.save(document);
    }

    public void deleteById(String id) {
        documentRepository.deleteById(id);
    }
}
