package com.part_of_fa.usuarios_documentos.document.entity;


import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import com.part_of_fa.usuarios_documentos.enums.TipoAutor;
import com.part_of_fa.usuarios_documentos.enums.TipoDocument;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Docu {
    @Id
    private String id;
    private String title;
    private String autor;
    private Date anno_public;
    private String cod_domicilio;
    private String isbn;
    private String dewey;
    private String publication;
    private String materia;
    private String edicion;
    private String idioma;
    private String pais;
    private String colacion;
    private String serie;
    private String letra_tit;
    private String notas;
    private String entrada;
    private TipoAutor tipo_autor;
    private String letras_entrada;
    private String evento;
    private TipoDocument tipo_doc;
    private String otros_autor;
    private String otros_titulos;
    private List<Ejemplar> ejemplares;



}
