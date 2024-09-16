package com.part_of_fa.usuarios_documentos.ejemplares.service;

import com.part_of_fa.usuarios_documentos.ejemplares.entity.Ejemplar;
import com.part_of_fa.usuarios_documentos.ejemplares.repository.EjemplarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class EjemplarService {
    private final EjemplarRepository ejemplarRepository;

//    Create
    public Ejemplar createEjemplar(Ejemplar ejemplar) {
        if(ejemplar != null){
            return ejemplarRepository.save(ejemplar);
        }else{
            return null;
        }
    }

//    Get
    public Ejemplar getEjemplar(String cod) {
        return ejemplarRepository.getOne(cod);
    }

//    Delete
    public Ejemplar deleteEjemplar(String cod) {
        Ejemplar ejemplar = ejemplarRepository.getOne(cod);
        ejemplarRepository.delete(ejemplar);
        return ejemplar;
    }

//    Update
    public Ejemplar updateEjemplar(String cod,Ejemplar ejemplar) {
       Ejemplar ejemplar1 = ejemplarRepository.getOne(cod);
       if(ejemplar1 != null){
           ejemplar1 = ejemplarRepository.save(ejemplar);
           return ejemplar1;
       }
       return null;
    }
}
