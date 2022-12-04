package com.hexacta.altapersonas.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hexacta.altapersonas.exception.PersonaNotFoundException;
import com.hexacta.altapersonas.exception.PersonaSaveErrorException;
import com.hexacta.altapersonas.model.Persona;
import com.hexacta.altapersonas.repository.PersonaRepository;
import com.hexacta.altapersonas.service.PersonaService;

@RestController
@RequestMapping("/persona")
@CrossOrigin("http://localhost:3000")
public class PersonaBusiness implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public Persona nuevaPersona(Persona nuevaPersona) {
        if (nuevaPersona.getNombre()==""||nuevaPersona.getDireccion()==""){
            throw new PersonaSaveErrorException();
        }

        nuevaPersona=categorizar(nuevaPersona);
        return personaRepository.save(nuevaPersona);
    }

    @Override
    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id)
        .orElseThrow(()->new PersonaNotFoundException(id));
    }

    @Override
    public Long countPersona() {
        return personaRepository.count();
    }

    @Override
    public Persona updatePersona(Persona nuevaPersona, int id) {
        return personaRepository.save(nuevaPersona);
    }

    @Override
    public String deletePersona(Long id) {
        if (!personaRepository.existsById(id)){
            throw new PersonaNotFoundException(id);
        }
        personaRepository.deleteById(id);
        return "Usuario con el id "+id+" ha sido eliminado";
    }



    @Override
    public Persona categorizar(Persona persona) {
        persona.categorizar();
        return persona;
    }

    @Override
    public List<Persona> getAllPersonas(Integer limit, Integer offset, String nombre, String direccion) {
        Pageable pageRequest = PageRequest.of(offset, limit);
        StringBuilder nombreBuilder = new StringBuilder("%" + nombre + "%");
        StringBuilder direccionBuilder = new StringBuilder("%" + direccion + "%");
        return personaRepository.findByNombreLikeAndDireccionLike
        (nombreBuilder.toString(), direccionBuilder.toString(), pageRequest);
    }

    
}
