// com.part_of_fa.usuarios_documentos.auth.model.AuthResponse.java
package com.part_of_fa.usuarios_documentos.utils.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String jwt;
}