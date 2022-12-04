package com.hexacta.altapersonas.service;

import com.hexacta.altapersonas.model.Persona;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/persona")
public interface PersonaService {


    @PostMapping("")
    Persona nuevaPersona(@RequestBody Persona nuevaPersona);

    Persona categorizar(Persona persona);

    @GetMapping("")
    List<Persona> getAllPersonas(@RequestParam(value="limit", required=false, defaultValue = "10")Integer limit,
                                 @RequestParam(value="offset", required=false, defaultValue = "0") Integer offset,
                                 @RequestParam(value="nombre", required=false, defaultValue = "") String nombre,
                                 @RequestParam(value="direccion", required=false, defaultValue = "") String direcicon);

    @GetMapping("/{id}")
    Persona getPersonaById(@PathVariable Long id);

    @GetMapping("/count")
    Long countPersona();

    @PutMapping("/{id}")
    Persona updatePersona(@RequestBody Persona nuevaPersona, @PathVariable int id);

    @DeleteMapping("/{id}")
    String deletePersona(@PathVariable Long id);

}
