package com.part_of_fa.usuarios_documentos.ejemplares.repository;

import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjemplarRepository extends JpaRepository<Ejemplar, String> {
}


