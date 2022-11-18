package com.hexacta.altapersonas.controller;

import com.hexacta.altapersonas.exception.PersonaNotFoundException;
import com.hexacta.altapersonas.model.Persona;
import com.hexacta.altapersonas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/persona")
    Persona nuevaPersona(@RequestBody Persona nuevaPersona){
        nuevaPersona=categorizar(nuevaPersona);
        return personaRepository.save(nuevaPersona);
    }
    Persona categorizar(Persona persona){
        int edadP = persona.getEdad();
        if (edadP<11){
            persona.setCategoria("Niños");
        }else if (edadP >=11 & edadP < 18){
            persona.setCategoria("Adolecentes");
        }else if (edadP >=18 & edadP < 80){
            persona.setCategoria("Adultos");
        }else if (edadP>=80){
            persona.setCategoria("Octogenario");
        }
        return persona;
    }

    @GetMapping("/personas")
    List<Persona> getAllPersonas(@RequestParam(value="limit", required=false)Integer limit,
                                 @RequestParam(value="offset", required=false) Integer offset,
                                 @RequestParam(value="nombre", required=false) String nombre,
                                 @RequestParam(value="direccion", required=false) String direccion){

        if (limit == null && offset == null &&
            limit.equals("") && offset.equals("")){
            return personaRepository.findAll();
        }
        // Ambos filtros aplicandose
        if (nombre != null && direccion != null &&
            !nombre.equals("") && !direccion.equals("")){
            return personaRepository.findByNameAddress(offset,limit,nombre+"%", direccion + "%");
        }
        if (nombre != null && !nombre.equals("")){
            return personaRepository.findByName(offset,limit,nombre+"%");
        }
        if (direccion != null && !direccion.equals("")){
            return personaRepository.findByAddress(offset,limit,direccion+"%");
        }
        // Only paginated
        return personaRepository.getPaginated(offset,limit);
    }

    @GetMapping("/persona/{id}")
    Persona getPersonaById(@PathVariable int id){
        return personaRepository.findById(id).orElseThrow(()->new PersonaNotFoundException(id));
    }

    @GetMapping("/persona/count")
    Integer countPeronsa(){
        return personaRepository.countRows();
    }

    @PutMapping("/persona/{id}")
    Persona updatePersona(@RequestBody Persona nuevaPersona, @PathVariable int id){
        return personaRepository.findById(id)
                .map(persona -> {
                    persona.setNombre(nuevaPersona.getNombre());
                    persona.setDireccion(nuevaPersona.getDireccion());
                    persona.setNacimiento(nuevaPersona.getNacimiento());
                    persona.setEdad(nuevaPersona.getEdad());
                    persona = categorizar(persona);
                    return personaRepository.save(persona);
                }).orElseThrow(()->new PersonaNotFoundException(id));
    }

    @DeleteMapping("/persona/{id}")
    String deletePersona(@PathVariable int id){
        if (!personaRepository.existsById(id)){
            throw new PersonaNotFoundException(id);
        }
        personaRepository.deleteById(id);
        return "Usuario con el id "+id+" ha sido eliminado";
    }

}
