package com.part_of_fa.usuarios_documentos.document.service;

import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.document.repository.DocumentRepository;
import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import com.part_of_fa.usuarios_documentos.ejemplares.service.EjemplarService;
import com.part_of_fa.usuarios_documentos.utils.exceptions.DuplicateEjemplarException;
import com.part_of_fa.usuarios_documentos.utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    private EjemplarService ejemplarService;

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
    public Docu addEjemplarToDocument(String documentId, String ejemplarCodBarra) {
        Optional<Docu> docuOptional = documentRepository.findById(documentId);
        Ejemplar ejemplar = ejemplarService.getEjemplar(ejemplarCodBarra);

        if (docuOptional.isEmpty()) {
            throw new ResourceNotFoundException("Document not found with id: " + documentId);
        }

        if (ejemplar == null) {
            throw new ResourceNotFoundException("Ejemplar not found with code: " + ejemplarCodBarra);
        }

        Docu docu = docuOptional.get();
        if (docu.getEjemplares().stream().anyMatch(e -> e.getId().equals(ejemplar.getId()))) {
            throw new DuplicateEjemplarException("Ejemplar already added to the document.");
        }

        docu.getEjemplares().add(ejemplar);
        return documentRepository.save(docu);
    }


    // Filtrar documentos por una característica
    public List<Docu> filterDocuments(Docu filterCriteria) {
        return documentRepository.findAll().stream()
                .filter(docu ->
                        (filterCriteria.getTitle() == null || docu.getTitle().equals(filterCriteria.getTitle())) &&
                                (filterCriteria.getAutor() == null || docu.getAutor().equals(filterCriteria.getAutor())) &&
                                (filterCriteria.getAnno_public() == null || docu.getAnno_public().equals(filterCriteria.getAnno_public())) &&
                                (filterCriteria.getCod_domicilio() == null || docu.getCod_domicilio().equals(filterCriteria.getCod_domicilio())) &&
                                (filterCriteria.getIsbn() == null || docu.getIsbn().equals(filterCriteria.getIsbn())) &&
                                (filterCriteria.getDewey() == null || docu.getDewey().equals(filterCriteria.getDewey())) &&
                                (filterCriteria.getPublication() == null || docu.getPublication().equals(filterCriteria.getPublication())) &&
                                (filterCriteria.getMateria() == null || docu.getMateria().equals(filterCriteria.getMateria())) &&
                                (filterCriteria.getEdicion() == null || docu.getEdicion().equals(filterCriteria.getEdicion())) &&
                                (filterCriteria.getIdioma() == null || docu.getIdioma().equals(filterCriteria.getIdioma())) &&
                                (filterCriteria.getPais() == null || docu.getPais().equals(filterCriteria.getPais())) &&
                                (filterCriteria.getColacion() == null || docu.getColacion().equals(filterCriteria.getColacion())) &&
                                (filterCriteria.getSerie() == null || docu.getSerie().equals(filterCriteria.getSerie())) &&
                                (filterCriteria.getLetra_tit() == null || docu.getLetra_tit().equals(filterCriteria.getLetra_tit())) &&
                                (filterCriteria.getNotas() == null || docu.getNotas().equals(filterCriteria.getNotas())) &&
                                (filterCriteria.getEntrada() == null || docu.getEntrada().equals(filterCriteria.getEntrada())) &&
                                (filterCriteria.getTipo_autor() == null || docu.getTipo_autor().equals(filterCriteria.getTipo_autor())) &&
                                (filterCriteria.getLetras_entrada() == null || docu.getLetras_entrada().equals(filterCriteria.getLetras_entrada())) &(filterCriteria.getEvento() == null || docu.getEvento().equals(filterCriteria.getEvento())) &&
                                (filterCriteria.getTipo_doc() == null || docu.getTipo_doc().equals(filterCriteria.getTipo_doc())) &&
                                (filterCriteria.getOtros_autor() == null || docu.getOtros_autor().equals(filterCriteria.getOtros_autor())) &&
                                (filterCriteria.getOtros_titulos() == null || docu.getOtros_titulos().equals(filterCriteria.getOtros_titulos()))
                ).toList();
    }




}
