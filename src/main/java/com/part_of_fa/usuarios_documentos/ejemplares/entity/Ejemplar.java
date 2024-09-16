package com.part_of_fa.usuarios_documentos.ejemplares.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Document(collection = "ejemplares")
@NoArgsConstructor
@AllArgsConstructor
public class Ejemplar {
    @Id
    private String cod_barra;
    private String subdivision_1;
    private String subdivision_2;
    private int no_ingreso;
    private Date fecha_ingreso;
    private String ubicacion;
    private String adquisicion_via;
    private String procedencia;
    private Long precio;
    private String estado;
}
