package com.part_of_fa.usuarios_documentos.solicitudes.entity;

import com.part_of_fa.usuarios_documentos.user.entity.User;
import com.part_of_fa.usuarios_documentos.document.entity.Docu;
import com.part_of_fa.usuarios_documentos.enums.EstadoSolicitud;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "solicitudes")
public class Solicitud {
    @Id
    private String id;
    private Date fechaSolicitud;
    private EstadoSolicitud estado;
    private User user;
    private Docu docu;
    
}