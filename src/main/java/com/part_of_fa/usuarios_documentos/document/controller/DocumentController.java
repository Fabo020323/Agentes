package com.part_of_fa.usuarios_documentos.document.controller;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.document.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    @GetMapping
    public List<Docu> getAllDocuments() {
        return documentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docu> getDocumentById(@PathVariable String id) {
        return documentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Docu createDocument(@RequestBody Docu document) {
        return documentService.save(document);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        documentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{documentId}/addEjemplar")
    public ResponseEntity<Docu> addEjemplarToDocument(@PathVariable String documentId, @RequestParam String ejemplarCodBarra) {
        Docu updatedDocu = documentService.addEjemplarToDocument(documentId, ejemplarCodBarra);
        if (updatedDocu != null) {
            return ResponseEntity.ok(updatedDocu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método en el controlador para filtrar documentos según los criterios
    @PostMapping("/filter")
    public List<Docu> filterDocuments(@RequestBody Docu filterCriteria) {
        return documentService.filterDocuments(filterCriteria);
    }

}
