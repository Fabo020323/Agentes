// com.part_of_fa.usuarios_documentos.auth.model.AuthRequest.java
package com.part_of_fa.usuarios_documentos.utils.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}

