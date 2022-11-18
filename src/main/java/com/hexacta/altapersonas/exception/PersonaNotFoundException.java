package com.hexacta.altapersonas.exception;

public class PersonaNotFoundException extends RuntimeException{
    public PersonaNotFoundException(int id){
        super("No se pudo encontrar el usuario con el id "+id);
    }
}
