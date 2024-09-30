package com.part_of_fa.usuarios_documentos.document.controller;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.document.service.DocumentService;
import com.part_of_fa.usuarios_documentos.utils.exceptions.DuplicateEjemplarException;
import com.part_of_fa.usuarios_documentos.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<Docu> getAllDocuments() {
        return documentService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Docu> getDocumentById(@PathVariable String id) {
        return documentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Docu> createDocument( @RequestBody Docu document) {
        Docu savedDocument = documentService.save(document);
        return ResponseEntity.ok(savedDocument);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{documentId}/addEjemplar")
    public ResponseEntity<Docu> addEjemplarToDocument(@PathVariable String documentId, @RequestParam String ejemplarCodBarra) {
        try {
            Docu updatedDocu = documentService.addEjemplarToDocument(documentId, ejemplarCodBarra);
            return ResponseEntity.ok(updatedDocu);
        } catch (ResourceNotFoundException | DuplicateEjemplarException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/filter")
    public List<Docu> filterDocuments( @RequestBody Docu filterCriteria) {
        return documentService.filterDocuments(filterCriteria);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
