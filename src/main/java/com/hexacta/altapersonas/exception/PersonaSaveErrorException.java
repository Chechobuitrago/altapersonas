package com.hexacta.altapersonas.exception;

public class PersonaSaveErrorException extends RuntimeException{
    public PersonaSaveErrorException(){
        super("Error en la validacion de la persona ingresada");
    }
}
