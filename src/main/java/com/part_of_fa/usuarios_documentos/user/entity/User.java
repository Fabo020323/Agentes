package com.part_of_fa.usuarios_documentos.user.entity;


import com.part_of_fa.usuarios_documentos.enums.NivelAcceso;
import com.part_of_fa.usuarios_documentos.enums.Permisos;
import com.part_of_fa.usuarios_documentos.enums.Tipo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name= "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String ci;
    @Enumerated(EnumType.STRING)
    private NivelAcceso nivel;
    private Tipo tipo;
    // Almacena múltiples géneros usando ElementCollection
    @ElementCollection(targetClass = Permisos.class)
    @CollectionTable(name = "user_permisos", joinColumns = @JoinColumn(name = "permisos_id"))
    @Enumerated(EnumType.STRING) // Almacena los enums como cadenas en la tabla secundaria
    @Column(name = "permisos")
    private List<Permisos> permisos;
}
