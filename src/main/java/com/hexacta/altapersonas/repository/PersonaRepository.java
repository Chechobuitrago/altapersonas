package com.hexacta.altapersonas.repository;

import com.hexacta.altapersonas.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    @Query (value="SELECT * FROM persona e where e.direccion like ?3 limit ?2 offset ?1 ", nativeQuery = true)
    public List<Persona> findByAddress(int offset, int limit, String direccion);

    @Query (value="SELECT * FROM persona e where e.nombre like ?3 limit ?2 offset ?1 ", nativeQuery = true)
    public List<Persona> findByName(int offset, int limit, String nombre);


    @Query (value="SELECT * FROM persona e where e.nombre like and ?3 and e.direccion like ?4 limit ?2 offset ?1 ", nativeQuery = true)
    public List<Persona> findByNameAddress(int offset, int limit, String nombre, String direccion);


    @Query (value="SELECT * FROM persona e limit ?2 offset ?1 ", nativeQuery = true)
    public List<Persona> getPaginated(int offset, int limit);

    @Query (value="SELECT count(id) FROM persona", nativeQuery = true)
    public Integer countRows();

}
