package com.hexacta.altapersonas.repository;

import com.hexacta.altapersonas.model.Persona;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {

    List<Persona> findByNombreLikeAndDireccionLike(
        String nombre,
        String direccion,
        Pageable pageable);

}
