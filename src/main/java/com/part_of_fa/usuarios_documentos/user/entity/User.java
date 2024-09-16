package com.part_of_fa.usuarios_documentos.user.entity;


import com.part_of_fa.usuarios_documentos.enums.NivelAcceso;
import com.part_of_fa.usuarios_documentos.enums.Permisos;
import com.part_of_fa.usuarios_documentos.enums.Tipo;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String ci;
    private NivelAcceso nivel;
    private Tipo tipo;
    // Almacena múltiples géneros usando ElementCollection
    private List<Permisos> permisos;
}
