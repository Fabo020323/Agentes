package com.part_of_fa.usuarios_documentos.prestamos.entity;

import com.part_of_fa.usuarios_documentos.user.entity.User;
import com.part_of_fa.usuarios_documentos.enums.EstadoPrestamo;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "prestamos")
public class Prestamo {
    @Id
    private String id;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private EstadoPrestamo estado;
    private User user;
    
}